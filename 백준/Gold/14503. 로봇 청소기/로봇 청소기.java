// 백준

import java.io.*;
import java.util.*;

public class Main {
    final static int UNCLEARED = 0;     // 청소되지 않은 칸
    final static int WALL = 1;          // 벽
    final static int CLEARED = 2;       // 청소된 칸

    static int N;  // Y
    static int M;  // X
    static int[][] board;

    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken()); // 청소기가 바라보는 방향. 0=북, 1=동, 2=남, 3=서

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operation(startY, startX, startD);

        bw.write(countCleared() + "\n");
        bw.flush();
    }

    public static void operation(int ny, int nx, int nd) {
        if (finished) {
            return;
        }

        // 현재칸 청소
        board[ny][nx] = 2;

        // 현재칸의 주변 4칸중 청소되지 않은 빈 칸 체크
        if (isFourDirectionAllCleared(ny, nx)) {
            // 주변 4칸이 모두 청소된 경우
            // 한칸 후진 시도
            if (canGoBack(ny, nx, nd)) {
                // 한칸 후진
                if (nd == 0) operation(ny + 1, nx, nd);
                else if (nd == 1) operation(ny, nx - 1, nd);
                else if (nd == 2) operation(ny - 1, nx, nd);
                else if (nd == 3) operation(ny, nx + 1, nd);
            } else {
                // 작동을 멈춘다.
                finished = true;
                return;
            }
        } else {
            // 주변 4칸중 청소되지 않은 칸이 하나라도 있는 경우
            // 반시계 방향으로 90도 회전한다
            nd = (nd - 1 + 4) % 4;

            // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우
            if (canGoFrontIfFrontUncleared(ny, nx, nd)) {
                // 한칸 전진
                if (nd == 0) operation(ny - 1, nx, nd);
                else if (nd == 1) operation(ny, nx + 1, nd);
                else if (nd == 2) operation(ny + 1, nx, nd);
                else if (nd == 3) operation(ny, nx - 1, nd);
            } else {
                operation(ny, nx, nd);
            }
        }
    }

    // 주변 4칸이 모두 청소되었는지 체크하는 메서드
    public static boolean isFourDirectionAllCleared(int ny, int nx) {
        boolean upCleared = (ny - 1 >= 0) && (board[ny - 1][nx] != UNCLEARED);
        boolean downCleared = (ny + 1 < N) && (board[ny + 1][nx] != UNCLEARED);
        boolean leftCleared = (nx - 1 >= 0) && (board[ny][nx - 1] != UNCLEARED);
        boolean rightCleared = (nx + 1 < M) && (board[ny][nx + 1] != UNCLEARED);
        return upCleared && downCleared && leftCleared && rightCleared;
    }

    // 후진 가능 여부 체크 메서드
    public static boolean canGoBack(int ny, int nx, int nd) {
        if (nd == 0) {
            // 바라보는 방향이 북쪽인 경우 -> 남쪽으로 갈수 있는지 체크
            return (ny + 1 < N) && (board[ny + 1][nx] != WALL);
        } else if (nd == 1) {
            // 바라보는 방향이 동쪽인 경우 -> 서쪽으로 갈수 있는지 체크
            return (nx - 1 >= 0) && (board[ny][nx - 1] != WALL);
        } else if (nd == 2) {
            // 바라보는 방향이 남쪽인 경우 -> 동쪽으로 갈수 있는지 체크
            return (ny - 1 >= 0) && (board[ny - 1][nx] != WALL);
        } else if (nd == 3) {
            // 바라보는 방향이 서쪽인 경우 -> 동쪽으로 갈수 있는지 체크
            return (nx + 1 < M) && (board[ny][nx + 1] != WALL);
        }
        return false;
    }

    // 앞쪽 칸이 청소되지 않은 빈칸인지 체크하는 메서드
    public static boolean canGoFrontIfFrontUncleared(int ny, int nx, int nd) {
        if (nd == 0) {
            return (ny - 1 >= 0) && (board[ny - 1][nx] == UNCLEARED);
        } else if (nd == 1) {
            return (nx + 1 < M) && (board[ny][nx + 1] == UNCLEARED);
        } else if (nd == 2) {
            return (ny + 1 < N) && (board[ny + 1][nx] == UNCLEARED);
        } else if (nd == 3) {
            return (nx - 1 >= 0) && (board[ny][nx - 1] == UNCLEARED);
        }
        return false;
    }

    // 청소된 칸의 개수 세는 메서드
    public static int countCleared() {
        int count = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (board[y][x] == CLEARED) {
                    count++;
                }
            }
        }
        return count;
    }
}
