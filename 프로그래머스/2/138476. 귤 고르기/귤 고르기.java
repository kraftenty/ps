import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        List<Integer> counts = new ArrayList(map.values());
        Collections.sort(counts, Collections.reverseOrder());
        
        int answer = 0;
        for (int c : counts) {
            answer++;
            k -= c;
            if (k <= 0) {
                break;
            }
        }
        return answer;
    }
}