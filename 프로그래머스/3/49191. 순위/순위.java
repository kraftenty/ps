class Solution {
    
    boolean[][] graph;
    
    public int solution(int n, int[][] results) {
        graph = new boolean[n+1][n+1];
        for (int[] r : results) {
            graph[r[0]][r[1]] = true;
        }
        
        // 플로이드 워셜 (거쳐가는 노드 포함시키기)
        for (int m=1; m<=n; m++) {
            for (int s=1; s<=n; s++) {
                for (int e=1; e<=n; e++) {
                    if (graph[s][m] && graph[m][e]) {
                        graph[s][e] = true;
                    }
                }
            }
        }
        
        // 각 선수별로 비교가능한 선수 카운트
        int answer = 0;
        for (int me=1; me<=n; me++) {
            int cnt = 0;
            for (int oth=1; oth<=n; oth++) {
                if (me==oth) continue;
                if (graph[me][oth] || graph[oth][me]) {
                    cnt++;
                }
            }
            
            if (cnt == n-1) {
                answer++;
            }
        }
        
        return answer;
    }
}