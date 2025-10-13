import java.util.*;

class Solution {
    
    List<Integer>[] adj;
    
    public int solution(int n, int[][] edge) {
        
        // 인접리스트 세팅
        adj = new ArrayList[n+1];
        for (int i=0; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] a : edge) {
            adj[a[0]].add(a[1]);
            adj[a[1]].add(a[0]);
        }
        
        
        // bfs
        int maxDist = 0;
        int[] d = new int[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        d[1] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : adj[cur]) {
                if (d[next] == 0) {
                    d[next] = d[cur] + 1;
                    maxDist = Math.max(maxDist, d[next]);
                    q.offer(next);
                }
            }
        }
        
        // 가장 멀리 떨어진 노드의 개수구하기
        int cnt = 0;
        for (int i=0; i<=n; i++) {
            if (d[i] == maxDist) {
                cnt++;
            }
        }
        
        
        return cnt;
    }
}