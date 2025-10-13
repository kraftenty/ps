class Solution {
    
    int n;
    boolean[] visited;
    int maxCnt;
    
    int[][] dungeons;
    
    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        n = dungeons.length;
        visited = new boolean[n];
        maxCnt = -1;
        
        dfs(0, k);
        
        return maxCnt;
    }
    
    void dfs(int cnt, int fatigue) {
        maxCnt = Math.max(maxCnt, cnt);
        
        for (int i=0; i<n; i++) {
            if (!visited[i] && fatigue >= dungeons[i][0]) {
                visited[i] = true;
                dfs(cnt+1, fatigue - dungeons[i][1]);
                visited[i] = false;
            }
        }
    }
}