import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        Map<Integer, Integer> endMap = new HashMap<>(); // 서버 끝 (시간, 대수)
        
        int answer = 0; // 증설 횟수 카운트
        int expandCnt = 0; // 현재 증설된 서버 대수
        for (int t=0; t<players.length; t++) {
            
            // 서버 끝
            if (endMap.containsKey(t)) {
                expandCnt -= endMap.get(t);
                endMap.remove(t);
            }
            
            // 서버 증설
            if (players[t] >= (m + m*expandCnt)) {
            	int val = players[t] - m*expandCnt;
            	int exp = val / m;
        		
                endMap.put(t+k, exp);
                answer += exp;
                expandCnt += exp;
            }
        }
        
        
        return answer;
    }
}