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
    
    boolean[][][] v = new boolean[101][101][4]; // y x d
    int minVal = Integer.MAX_VALUE;
    int R,C;
    char[][] b;
    
    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();
        b = new char[R][C];
        
        int sy=0, sx=0;
        for (int y=0; y<R; y++) {
            char[] charr = board[y].toCharArray();
            for (int x=0; x<C; x++) {
                b[y][x] = charr[x];
                if (b[y][x]=='R') {
                    sy = y;
                    sx = x;
                }
            }
        }
        
        bfs(sy, sx);
        if (minVal == Integer.MAX_VALUE) return -1;
        return minVal;
    }
    
    void bfs(int sy, int sx) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sy, sx, 0));
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (b[cur.y][cur.x]=='G') {
                minVal = Math.min(minVal, cur.cnt);
                continue;
            }
            
            for (int d=0; d<4; d++) {
                int hop=0;
                int y=cur.y;
                int x=cur.x;
                while (true) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny<0 || ny>=R || nx<0 || nx>=C || b[ny][nx]=='D') {
                        break;
                    }
                    hop++;
                    y = ny;
                    x = nx;
                }
                
                if (hop==0 || v[y][x][d]) {
                    continue;
                }
                
                q.offer(new Node(y, x, cur.cnt+1));
                v[y][x][d] = true;
            }
        }
    }
}