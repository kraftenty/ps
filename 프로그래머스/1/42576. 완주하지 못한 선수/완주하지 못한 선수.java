import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        // 완주자 맵 만들기
        Map<String, Integer> map = new HashMap<>();
        for (String s : completion) {
            map.put(s, map.getOrDefault(s, 0) + 1);     
        }
        
        // 참가자 배열 돌면서 확인
        String answer = "";
        for (String s : participant) {
            if (!map.containsKey(s) || map.get(s) <= 0) {
                answer = s;
                break;
            }
            map.put(s, map.get(s) - 1);
        }
        
        return answer;
    }
}