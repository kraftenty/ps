import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int y, x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N, K;
    static int[][] board;
    static int[] parent;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        parent = new int[K + 1];
        Queue<Point> q = new ArrayDeque<>();
        
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            board[y][x] = i;
            parent[i] = i;
            q.offer(new Point(y, x));
        }


        int civCount = K;
        for (Point p : q) {
            int curCiv = board[p.y][p.x];
            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    int nextCiv = board[ny][nx];
                    if (nextCiv != 0 && nextCiv != curCiv && union(curCiv, nextCiv)) {
                        civCount--;
                    }
                }
            }
        }

        int year = 0;
        while (civCount > 1) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point p = q.poll();
                int curCiv = board[p.y][p.x];
                // 주변에 문명확산
                for (int d = 0; d < 4; d++) {
                    int ny = p.y + dy[d];
                    int nx = p.x + dx[d];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && board[ny][nx] == 0) {
                        board[ny][nx] = curCiv;
                        q.offer(new Point(ny, nx));

                        // 문명 유니온
                        for (int nd = 0; nd < 4; nd++) {
                            int nny = ny + dy[nd];
                            int nnx = nx + dx[nd];
                            if (nny >= 0 && nny < N && nnx >= 0 && nnx < N) {
                                int nextCiv = board[nny][nnx];
                                if (nextCiv != 0 && nextCiv != curCiv && union(curCiv, nextCiv)) {
                                    civCount--;
                                }
                            }
                        }
                    }
                }
            }
            year++;
        }

        System.out.println(year);

    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) { // 유니온 될 때마다 true반환
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }
}