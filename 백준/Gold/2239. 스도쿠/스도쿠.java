// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new int[9][9];
        for (int y = 0; y < 9; y++) {
            String input = br.readLine();
            for (int x = 0; x < 9; x++) {
                board[y][x] = input.charAt(x) - '0';
            }
        }

        // dfs 수행
        dfs(0, 0);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sb.append(board[y][x]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dfs(int y, int x) {
        if (y == 9) {
            finished = true;
            return;
        }

        // board[y][x]에 이미 숫자가 채워져 있으면
        if (board[y][x] != 0) {
            // 다음칸 이동
            if (x == 8) dfs(y + 1, 0);
            else dfs(y, x + 1);
        } else { // board[y][x]가 0 이면
            for (int i = 1; i <= 9; i++) {
                // 넣을 수 없는 칸이면 거르기
                if (!canPut(y, x, i)) {
                    continue;
                }

                // 숫자 넣고
                board[y][x] = i;

                // 다음칸 이동
                if (x == 8) dfs(y + 1, 0);
                else dfs(y, x + 1);

                // 다 넣었으면 dfs 중지
                if (finished) {
                    return;
                }

                // 백트래킹 (쭉 가봤는데 다 실패했으면 하나씩 다 돌아가면서 칸에 넣었던거 비워야지)
                board[y][x] = 0;
            }
        }
    }

    // 보드에 숫자를 넣을 수 있는지 검사하는 메서드
    public static boolean canPut(int numY, int numX, int num) {
        // 가로줄 검사
        for (int x = 0; x < 9; x++) {
            if (board[numY][x] == num) {
                return false;
            }
        }

        // 세로줄 검사
        for (int y = 0; y < 9; y++) {
            if (board[y][numX] == num) {
                return false;
            }
        }

        // 3*3 네모박스 검사
        int startY = numY / 3 * 3;
        int startX = numX / 3 * 3;
        for (int y = startY; y < startY + 3; y++) {
            for (int x = startX; x < startX + 3; x++) {
                if (board[y][x] == num) {
                    return false;
                }
            }
        }

        // 다 통과했으면 true 반환
        return true;
    }
}