import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        
        Set<String> set = new HashSet<>();
        int num = 1;
        int round = 1;
        String prevWord = null;
        boolean nobodyFail = true;
        for (String word : words) {
            boolean fail = false;
            if (!(prevWord == null)) {
                // 한글자 단어
                if (word.length() == 1) {
                    fail = true;
                }
                // 이미 말한 단어
                else if (set.contains(word)) { 
                    fail = true;
                }
                // 이어지지 않는 단어
                else if (prevWord.charAt(prevWord.length()-1) != word.charAt(0)) {
                    fail = true;
                }
            }
            
            // 실패 시
            if (fail) {
                answer[0] = num;
                answer[1] = round;
                nobodyFail = false;
                break;
            }
            
            // 성공 시
            set.add(word);
            prevWord = word;
            
            // 순회
            num++;
            if (num > n) {
                num = 1;
                round++;
            }
        }

        if (nobodyFail) {
            return new int[] {0, 0};
        }
        return answer;
    }
}