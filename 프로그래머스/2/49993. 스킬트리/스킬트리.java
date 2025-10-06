import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (String user : skill_trees) {
            if (check(skill, user)) {
                answer++;
            }
        }
        return answer;
    }
    
    boolean check(String skill, String user) {
        int skillIdx = 0;
        char[] chArr = user.toCharArray();
        for (char c : chArr) {
            int curIdx = skill.indexOf(c);
            
            if (curIdx == -1) {
                continue;
            }
            
            if (curIdx==skillIdx) {
                skillIdx++;
            } else {
                return false;
            }
        }
        
        return true;
    }
    
}