import java.io.*;
import java.util.*;

class Point {
    int y;
    int x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N;
    static int M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); // br.readLine() 은 IOException 을 발생시킨다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int y = 0; y < N; y++) {
            String line = br.readLine();
            for (int x = 0; x < M; x++) {
                board[y][x] = line.charAt(x) - '0';
            }
        }


        bfs(0, 0);
        System.out.println(board[N-1][M-1]);
    }

    public static void bfs(int sy, int sx) {
        final int[][] directions = new int[][] {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        Queue<Point> dq = new ArrayDeque<>();

        dq.offer(new Point(sy, sx));

        while (!dq.isEmpty()) {
            Point curPoint = dq.poll();
            for (int[] direction : directions) {
                int ny = curPoint.y + direction[0];
                int nx = curPoint.x + direction[1];
                // border checking
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }

                if (board[ny][nx] == 1) {
                    board[ny][nx] = board[curPoint.y][curPoint.x] + 1;
                    dq.offer(new Point(ny, nx));
                }
            }
        }
    }
}