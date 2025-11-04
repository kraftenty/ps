import java.util.*;

class Solution {
    
   
    
    public int[] solution(String[] gems) {
        Set<String> kinds = new HashSet<>(Arrays.asList(gems));
        int totalSize = kinds.size();
        
        Map<String, Integer> map = new HashMap<>();
        
        int lt=0; 
        int[] answer = new int[] {1, gems.length};
        
        int minLen = Integer.MAX_VALUE;
        
        for (int rt=0; rt<gems.length; rt++) {
            map.put(gems[rt], map.getOrDefault(gems[rt], 0) + 1);
            
            while (map.size() == totalSize) {
                if (rt-lt < minLen) {
                    minLen = rt-lt;
                    answer[0] = lt+1;
                    answer[1] = rt+1;
                }

                map.put(gems[lt], map.get(gems[lt])-1);
                if (map.get(gems[lt])==0) {
                    map.remove(gems[lt]);
                }
                
                lt++;
            }
        }
        return answer;
    }
    
}