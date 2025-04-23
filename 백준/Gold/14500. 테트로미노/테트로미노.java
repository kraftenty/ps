// 백준

import java.io.*;
import java.util.*;

@FunctionalInterface
interface ShapeFunction {
    int[][] apply(int y, int x);
}

public class Main {
    static int N; // 세로 크기
    static int M; // 가로 크기
    static int[][] board;

    static List<ShapeFunction> shapes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        shapes = Arrays.asList(
                // I (2가지)
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y, x + 2}, {y, x + 3}},
                (y, x) -> new int[][]{{y, x}, {y + 1, x}, {y + 2, x}, {y + 3, x}},

                // O (1가지)
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y + 1, x}, {y + 1, x + 1}},

                // L (8가지 회전/대칭)
                (y, x) -> new int[][]{{y, x}, {y + 1, x}, {y + 2, x}, {y + 2, x + 1}},   // L1
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y, x + 2}, {y + 1, x}},       // L2
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y + 1, x + 1}, {y + 2, x + 1}},// L3
                (y, x) -> new int[][]{{y, x + 2}, {y + 1, x}, {y + 1, x + 1}, {y + 1, x + 2}},// L4
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y + 1, x}, {y + 2, x}},       // L5 (좌하 ㄴ)
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y, x + 2}, {y + 1, x + 2}},   // L6 (ㄴ자 반시계 회전)
                (y, x) -> new int[][]{{y, x + 1}, {y + 1, x + 1}, {y + 2, x + 1}, {y + 2, x}}, // L7 (ㄴ자 좌우반전)
                (y, x) -> new int[][]{{y, x}, {y + 1, x}, {y + 1, x + 1}, {y + 1, x + 2}}, // L8 (ㄴ자 회전반대)

                // Z (4가지)
                (y, x) -> new int[][]{{y, x}, {y + 1, x}, {y + 1, x + 1}, {y + 2, x + 1}}, // Z1
                (y, x) -> new int[][]{{y, x + 1}, {y, x + 2}, {y + 1, x}, {y + 1, x + 1}}, // Z2
                (y, x) -> new int[][]{{y, x + 1}, {y + 1, x + 1}, {y + 1, x}, {y + 2, x}}, // Z3 (Z1 반전)
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y + 1, x + 1}, {y + 1, x + 2}}, // Z4 (Z2 반전)

                // T (4가지)
                (y, x) -> new int[][]{{y, x}, {y, x + 1}, {y, x + 2}, {y + 1, x + 1}},   // T1
                (y, x) -> new int[][]{{y, x + 1}, {y + 1, x}, {y + 1, x + 1}, {y + 2, x + 1}}, // T2
                (y, x) -> new int[][]{{y, x + 1}, {y + 1, x}, {y + 1, x + 1}, {y + 1, x + 2}}, // T3
                (y, x) -> new int[][]{{y, x}, {y + 1, x}, {y + 1, x + 1}, {y + 2, x}}    // T4
        );

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSum = Integer.MIN_VALUE;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                maxSum = Math.max(maxSum, getMaxSumOnPoint(y, x));
            }
        }

        System.out.println(maxSum);

    }

    public static int getMaxSumOnPoint(int cy, int cx) {
        int maxSum = Integer.MIN_VALUE;
        for (ShapeFunction shapeFunction : shapes) {
            int sum = 0;
            boolean correctShape = true;
            int[][] points = shapeFunction.apply(cy, cx);
            for (int[] point : points) {
                int ny = point[0];
                int nx = point[1];
                if (ny < 0 || ny > (N - 1) || nx < 0 || nx > (M - 1)) {
                    correctShape = false;
                    break;
                }
                sum += board[ny][nx];
            }
            if (correctShape) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

}