import java.util.*;

class Solution {
    
    ArrayList<Integer>[] graph;
    boolean[] visited;
    int cnt;
    
    public int solution(int n, int[][] wires) {
        graph = new ArrayList[n+1];
        for (int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 그래프 만들기(양방향)
        for (int[] wire : wires) {
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }
        
        
        // dfs
        int answer = Integer.MAX_VALUE;
        for (int i=0; i<n-1; i++) {
            visited = new boolean[n+1];
            cnt = 0;
            
            int n1 = wires[i][0];
            int n2 = wires[i][1];
            
            // 제외
            graph[n1].remove((Integer)n2);
            graph[n2].remove((Integer)n1);
            
            // dfs
            dfs(1);
            
            answer = Math.min(answer, Math.abs(cnt - (n-cnt)));
            
            // 다시 포함
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        
        return answer;
    }
    
    void dfs(int cur) {
        visited[cur] = true;
        cnt++;
        for (int next : graph[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }
}