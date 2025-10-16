import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        Integer[] boxs = new Integer[numbers.length];
        for (int i=0; i<boxs.length; i++) {
            boxs[i] = numbers[i];
        }
        
        Arrays.sort(boxs, (a,b) -> (b + "" + a).compareTo(a + "" + b));
        
        if (boxs[0] == 0) {
            return "0";
        }
        
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<boxs.length; i++) {
            sb.append(boxs[i]);
        }
        return sb.toString();
    }
}