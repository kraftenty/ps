import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> winSet = new HashSet<>();
        for (int i : win_nums) {
            winSet.add(i);
        }
        
        int count = 0;
        int zeroCount = 0;
        for (int i : lottos) {
            if (winSet.contains(i)) {
                count++;
            } else if (i == 0) {
                zeroCount++;
            }
        }
        
        int[] answer = new int[2];
        answer[0] = 7 - count - zeroCount;
        answer[1] = 7 - count;
        if (answer[0] == 7) {
            answer[0] = 6;
        }
        if (answer[1] == 7) {
            answer[1] = 6;
        }
        return answer;
    }
}