import java.util.*;

class Node {
    int y,x;
    public Node(int y, int x) {
        this.y=y;
        this.x=x;
    }
}

class Solution {
    
    int[][] land;
    int R;
    int C;
    
    final static int[] dy = {-1, 1, 0, 0};
    final static int[] dx = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        this.land = land;
        R = land.length;
        C = land[0].length;
        Map<Integer, Integer> map = new HashMap<>(); // 덩어리번호, 카운트
            
        // 석유 덩어리 나누기
        int num=2;
        for (int y=0; y<R; y++) {
            for (int x=0; x<C; x++) {
                if (land[y][x] == 1) {
                    int cnt = bfs(y, x, num);
                    map.put(num, cnt);
                    num++;
                }
            }
        }
        
        // 실제 시추
        int answer = 0;
        Set<Integer> set;
        for (int x=0; x<C; x++) {
            set = new HashSet<>();
            for (int y=0; y<R; y++) {
                if (land[y][x]==0) continue;
                set.add(land[y][x]);
            }
            int sum = 0;
            for (int elem : set) {
                sum += map.get(elem);
            }
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    int bfs(int sy, int sx, int num) {
        int ret = 1;
        
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sy, sx));
        land[sy][sx] = num;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            for (int i=0; i<4; i++) {
                int ny = cur.y+dy[i];
                int nx = cur.x+dx[i];
                if (ny>=0 && ny<R && nx>=0 && nx<C && land[ny][nx]==1) {
                    land[ny][nx] = num;
                    ret++;
                    q.offer(new Node(ny, nx));
                }
            }
            
        }
        
        return ret;
    }
}