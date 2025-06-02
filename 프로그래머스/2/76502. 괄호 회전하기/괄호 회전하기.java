import java.util.*;

class Solution {
    
    public boolean match(char a, char b) {
        if (a == ')' && b == '(')
            return true;
        else if (a == ']' && b == '[')
            return true;
        else if (a == '}' && b == '{')
            return true;
        else 
            return false;
    }
    
    public int solution(String s) {
        int len = s.length();
        s += s;
        
        int answer = 0;
        A: for (int i=0; i<len; i++) {
            Stack<Character> stack = new Stack<>();
            for (int j=i; j<i+len; j++) {
                char c = s.charAt(j);
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || !match(c, stack.pop())) {
                        continue A;
                    }
                }
            }
            
            if (stack.isEmpty()) {
                answer++;
            }
        }
        

        return answer;
    }
}