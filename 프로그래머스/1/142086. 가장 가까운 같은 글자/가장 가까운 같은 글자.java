import java.util.*;

class Solution {
    public int[] solution(String s) {
        
        int[] arr = new int[256];
        Arrays.fill(arr, -1);
        
        int[] answer = new int[s.length()];
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (arr[c] == -1) {
                answer[i] = -1;
            } else {
                answer[i] = i - arr[c];
            }
            
            arr[c] = i;
        }
        
        return answer;
    }
}