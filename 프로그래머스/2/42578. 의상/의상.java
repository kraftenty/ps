import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }
        
        // 각 종류마다 경우의수 곱하기 (안입는경우 포함)
        int ans = 1;
        for (int n : map.values()) {
            ans *= (n+1);
        }
        
        // 아무것도 안입는 경우 제외
        ans--;
        
        return ans;
    }
}