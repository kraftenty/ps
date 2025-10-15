import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        
        int answer = 0;
        while (pq.peek() < K && pq.size() >= 2) {
            
            // 음식 뽑기
            int first = pq.poll();
            int second = pq.poll();
            
            // 새로운 음식 섞기
            int newFood = first + (second * 2);
            pq.offer(newFood);

            answer++;
        }
        
        if (pq.peek() >= K) {
            return answer;
        } else {
            return -1;
        }
    }
}