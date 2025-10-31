import java.util.*;

class Node {
    int y,x;
    public Node(int y, int x) {
        this.y=y;
        this.x=x;
    }
}

class Solution {
    
    int[][] board;
    boolean[][] v;
    int R, C;
    
    final static int[] dy = {-1, 1, 0, 0};
    final static int[] dx = {0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        board = new int[R][C];
        v = new boolean[R][C];
        
        for(int y=0; y<R; y++) {
            char[] charr = maps[y].toCharArray();
            for (int x=0; x<C; x++) {
                if (charr[x]=='X') board[y][x] = 0;
                else board[y][x] = charr[x]-'0';
            }
        }
        
        List<Integer> li = new ArrayList<>();
        for (int y=0; y<R; y++) {
            for (int x=0; x<C; x++) {
                if (board[y][x]==0 || v[y][x]) continue;
                int ret = bfs(y, x);
                li.add(ret);
            }
        }
        
        if (li.isEmpty()) return new int[] {-1};
        
        int[] answer = new int[li.size()];
        for (int i=0; i<li.size(); i++) {
            answer[i] = li.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
    
    int bfs(int sy, int sx) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sy, sx));
        v[sy][sx] = true;
        
        int ret=board[sy][sx];
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i=0; i<4; i++) {
                int ny=cur.y+dy[i];
                int nx=cur.x+dx[i];
                if (ny>=0 && ny<R && nx>=0 && nx<C && !v[ny][nx] && board[ny][nx]!=0) {
                    v[ny][nx] = true;
                    ret += board[ny][nx];
                    q.offer(new Node(ny, nx));
                }
            }
        }
        
        return ret;
    }
}