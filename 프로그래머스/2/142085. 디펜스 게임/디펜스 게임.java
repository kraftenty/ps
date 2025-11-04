import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        int i;
        for (i=0; i<enemy.length; i++) {
            n -= enemy[i];
            q.offer(enemy[i]);
            
            if (n < 0) {
                if (k == 0) break;
                
                k--;
                n += q.poll();
            }
            
        }
        
        return i;
    }
}