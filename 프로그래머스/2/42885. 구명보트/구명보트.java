import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        // 50 50 70 80
        Arrays.sort(people);
        int answer = 0;
        int lt = 0;
        int rt = people.length - 1;
        while (lt <= rt) {
            if (people[lt] + people[rt] > limit) {
                rt--;
                answer++;
            } else {
                lt++;
                rt--;
                answer++;
            }
        }
        return answer;
    }
}