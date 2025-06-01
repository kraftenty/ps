import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[N];

        // 가격 변곡점 찾기
        for (int i=0; i<N; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int topIndex = stack.pop();
                result[topIndex] = i - topIndex;
            }
            stack.push(i);
        }

        // 쭉 유지된 애들 처리
        for (int i=0; i<N; i++) {
            if (result[i] == 0) {
                result[i] = N-1-i;
            }
        }

        return result;
    }
}
