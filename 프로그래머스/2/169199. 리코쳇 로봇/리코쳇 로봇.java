import java.util.*;

class Node {
    int y,x,cnt;
    public Node(int y, int x, int cnt) {
        this.y=y;
        this.x=x;
        this.cnt=cnt;
    }
}

class Solution {
    // 상 하 좌 우
    final static int[] dy = {-1, 1, 0, 0};
    final static int[] dx = {0, 0, -1, 1};
    
    boolean[][] v = new boolean[100][100]; // y x
    int R,C;
    char[][] b;
    
    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();
        b = new char[R][C];
        
        int sy=0, sx=0;
        for (int y=0; y<R; y++) {
            b[y] = board[y].toCharArray();
            for (int x=0; x<C; x++) {
                if (b[y][x]=='R') {
                    sy = y;
                    sx = x;
                }
            }
        }
        
        return bfs(sy, sx);
    }
    
    int bfs(int sy, int sx) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sy, sx, 0));
        v[sy][sx] = true;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (b[cur.y][cur.x]=='G') {
                return cur.cnt;
            }
            
            for (int d=0; d<4; d++) {
                int y=cur.y;
                int x=cur.x;
                while (true) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny<0 || ny>=R || nx<0 || nx>=C || b[ny][nx]=='D') {
                        break;
                    }
                    y = ny;
                    x = nx;
                }
                
                if (v[y][x]) continue;
                
                q.offer(new Node(y, x, cur.cnt+1));
                v[y][x] = true;
            }
        }
        
        return -1;
    }
}