import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();
        for (int i : lost) {
            lostSet.add(i);
        }
        for (int i : reserve) {
            reserveSet.add(i);
        }
        
        // 여벌이 있고 도난당한 학생 제거
        for (int i=1; i<=n; i++) {
            if (lostSet.contains(i) && reserveSet.contains(i)) {
                lostSet.remove(i);
                reserveSet.remove(i);
            }
        }
        
        int answer = 0;
        for (int i=1; i<=n; i++) {
            if (!lostSet.contains(i)) {
                answer++;
                continue;
            }

            // 전 친구
            if (i>1 && reserveSet.contains(i-1)) {
                answer++;
                continue;
            }
            
            // 후 친구
            if (i<n && reserveSet.contains(i+1)) {
                answer++;
                reserveSet.remove(i+1);
            }
        }
        return answer;
    }
}