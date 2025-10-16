import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Stack<Integer> st = new Stack<>();
        
        for (int i=0; i<n; i++) {
            while (!st.isEmpty() && numbers[st.peek()] < numbers[i]) {
                int a = st.pop();
                answer[a] = numbers[i];
            }
            st.push(i);
        }
        return answer;
    }
}