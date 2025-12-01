import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        Queue<Integer> pq = new PriorityQueue<>();
        
        for (int i=0; i<score.length; i++) {
            if (pq.size() < k) {
                pq.offer(score[i]);
            } else {
                pq.offer(score[i]);
                pq.poll();
                
            }
            
            answer[i] = pq.peek();
        }
        return answer;
    }
}