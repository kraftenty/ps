import java.util.*;

class Node {
    int y,x;
    public Node(int y, int x){ 
        this.y=y;
        this.x=x;
    }
}

class Solution {
    
    char[][] board;
    int R, C;
    
    final static int[] dy = {-1, 1, 0, 0};
    final static int[] dx = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        int sy=0, sx=0;
        int ly=0, lx=0;
        int ey=0, ex=0;
        board = new char[R][C];
        
        for (int y=0; y<R; y++) {
            for (int x=0; x<C; x++) {
                board[y][x] = maps[y].charAt(x);
                if (board[y][x] == 'S') {
                    sy = y;
                    sx = x;
                } else if (board[y][x] == 'L') {
                    ly = y;
                    lx = x;
                } else if (board[y][x] == 'E') {
                    ey = y;
                    ex = x;
                }
            }
        }
        
        int answer = 0;
        // 1. S->L
        int res1 = bfs(sy, sx, ly, lx);
        if (res1 < 0) {
            return -1;
        }
        answer += res1;
        
        // 2. L->E
        int res2 = bfs(ly, lx, ey, ex);
        if (res2 < 0) {
            return -1;
        }
        answer += res2;
        
        return answer;
    }
    
    int bfs(int sy, int sx, int ey, int ex) {
        int[][] v = new int[R][C];
        v[sy][sx] = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sy, sx));
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            for (int i=0; i<4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny>=0 && ny<R && nx>=0 && nx<C && board[ny][nx]!='X' && v[ny][nx]==0) {
                    v[ny][nx] = v[cur.y][cur.x] + 1;
                    q.offer(new Node(ny, nx));
                }
            }
        }
        
        if (v[ey][ex] == 0) {
            return -1;
        }
        return v[ey][ex]-1;
    }
}