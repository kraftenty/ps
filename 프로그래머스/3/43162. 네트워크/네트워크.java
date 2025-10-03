import java.util.*;

class Solution {

	static int N;
    static boolean[] v;
    int ans;
    
    public int solution(int n, int[][] computers) {
    	N = n;
        v = new boolean[N];
        ans = 0;
        
        for (int i=0; i<N; i++) {
            if (!v[i]) {
                v[i] = true;
                ans++;
            }
            bfs(i, computers);
        }
        
        return ans;
    }
    
    void bfs(int s, int[][] computers) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i=0; i<N; i++) {
                if (computers[cur][i]==1 && !v[i]) {
                    v[i] = true;
                    q.add(i);
                }
            }
        }
    }
}