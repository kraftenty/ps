import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        Arrays.sort(score);
        
        int answer = 0;
        int idx = score.length;
        while (true) {
            idx -= m;
            if (idx<0) break;
            answer += (score[idx] * m);
        }
        
        return answer;
    }
}