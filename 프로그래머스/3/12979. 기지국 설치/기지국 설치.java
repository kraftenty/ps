import java.util.*;

class Solution {
    
    int coverage;
    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        coverage = 2*w+1;
        int prevRight = 0;
        for (int s : stations) {
            int lt = Math.max(1, s-w);
            int rt = Math.min(n, s+w);
            
            answer += calc(prevRight+1, lt-1);
            prevRight = rt;
        }
        
        answer += calc(prevRight+1, n);
        
        return answer;
    }
    
    int calc(int s, int e) {
        int len = e-s+1;
        if (len<=0) return 0;
        return (int)Math.ceil((double)len / coverage);
    }

}