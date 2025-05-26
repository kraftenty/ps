import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int size = board.length;
        Stack<Integer> stack = new Stack<>();
        
        int answer = 0;
        for (int move : moves) {
            for (int j=0; j<size; j++) {
                int doll = board[j][move-1];
                if (doll != 0) {
                    if (!stack.isEmpty() && doll == stack.peek()) {
                        stack.pop();
                        answer += 2;
                    } else {
                        stack.push(doll);
                    }
                    board[j][move-1] = 0;
                    break;
                }
            }
        } 
        return answer;
    }
}