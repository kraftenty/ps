import java.util.*;

class Solution {
    boolean solution(String s) {
        int val = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                val++;
            } else if (val > 0 && c == ')') {
                val--;
            } else {
                return false;
            }
        }
        return val == 0;
    }
}