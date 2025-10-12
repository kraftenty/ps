import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        
        Arrays.sort(times);
        
        return binSearch(n, times);
    }
    
    long binSearch(int n, int[] times) {
        long lt = 1;
        long rt = (long) times[times.length-1] * n; // 모든 사람이 가장 느린 심사관한테 가는 경우
        long answer = rt;
        
        while (lt <= rt) {
            long md = (lt + rt) / 2;
            long sum = 0; // md분 동안 심사할 수 있는 사람의 수
            for (int i=0; i<times.length; i++) {
                sum += (md / times[i]);
                if (sum > n) break;
            }
            
            if (sum >= n) {
                answer = md;
                rt = md - 1;
            } else { // n명 미만으로 통과하는 경우
                lt = md + 1;
            }
        }
        
        return answer;
    }
}