import java.util.*;

class Solution {
    
    class Point {
        public int y;
        public int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public int solution(int[][] maps) {
        int answer = -1;
        int N = maps.length;
        int M = maps[0].length;
        
        int[] dy = new int[]{-1, 1, 0, 0};
        int[] dx = new int[]{0, 0, 1, -1};
        
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cy = cur.y;
            int cx = cur.x;
            if(cy == N-1 && cx == M-1) {
                answer = maps[N-1][M-1];
                break;
            }
            for (int i=0; i<4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && maps[ny][nx] == 1) {
                    maps[ny][nx] = maps[cy][cx] + 1;
                    q.offer(new Point(ny, nx));
                }
            }
        }        

        return answer;
    }
}