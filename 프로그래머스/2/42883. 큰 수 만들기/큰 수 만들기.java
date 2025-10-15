import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : number.toCharArray()) {
            while (!stack.isEmpty() && k>0 && stack.peek() < c) {
                stack.pop();
                k--;
            }
            
            stack.push(c);
        }
        
        // 아직 다 못지웠을때
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : stack) { // 스택 바닥에서부터
            sb.append(c);
        }
        return sb.toString();
    }
}