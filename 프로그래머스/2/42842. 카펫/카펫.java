import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        List<Integer> cols = getColList(yellow); // 1,2,3,4,6,8,12, 24
        for(int col : cols) {
            int row = yellow / col;
            if (col > row) {
                break;
            }
            if (((row+2)*2 + col*2) == brown) {
                answer[0] = row+2;
                answer[1] = col+2;
                break;
            }
            
        }
        return answer;
    }
    
    public List<Integer> getColList(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=num; i++) {
            if (num % i == 0) {
                list.add(i);
            }
        }
        return list;
    }
}