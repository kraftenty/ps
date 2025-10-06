import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : works) {
            q.offer(i);
        }
        
        // 메인로직
        for (int i=0; i<n; i++) {
            int w = q.poll();
            if (w > 0) {
                w--;
            }
            q.offer(w);
        }
        
        // 야근지수 계산
        long answer = 0;
        while (!q.isEmpty()) {
            int w = q.poll();
            answer += Math.pow(w, 2);
        }
        return answer;
    }
}