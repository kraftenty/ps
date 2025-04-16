import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];
            for (int i=0; i<commands.length; i++) {
                int startIdx = commands[i][0] - 1;
                int endIdx = commands[i][1] - 1;
                int queryIdx = commands[i][2] - 1;

                int[] slicedArray = Arrays.copyOfRange(array, startIdx  , endIdx+1);
                Arrays.sort(slicedArray);
                answer[i] = slicedArray[queryIdx];  
            }
            return answer;
        }
}