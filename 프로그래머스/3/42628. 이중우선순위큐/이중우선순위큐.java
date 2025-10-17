import java.util.*;

class Solution {

    public int[] solution(String[] operations) {
        Queue<Integer> minQ = new PriorityQueue<>();
        Queue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Integer> map = new HashMap<>();
        
        for (String s : operations) {
            String[] cmd = s.split(" ");
            String op = cmd[0];
            int num = Integer.parseInt(cmd[1]);
            
            if (op.equals("I")) {
                minQ.offer(num);
                maxQ.offer(num);
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else {
                if (op.equals("D")) {
                    if (map.isEmpty()) { // 삭제할것이 없으면 스킵
                        continue;
                    }
                    
                    if (num==1) { // 최댓값삭제
                        deleteFromQ(maxQ, map);
                    } else { // 최솟값삭제
                        deleteFromQ(minQ, map);
                    }
                }
            }
        }
        
        clean(minQ, map);
        clean(maxQ, map);
        
        if (minQ.size() == 0) {
            return new int[] {0, 0};
        } 
        
        return new int[] {maxQ.peek(), minQ.peek()};
    }
    
    void deleteFromQ(Queue<Integer> q, Map<Integer,Integer> map) {
        clean(q, map);
        
        int polled = q.poll();
        map.put(polled, map.get(polled) - 1);
        if (map.get(polled) == 0) {
            map.remove(polled);
        } 
    }
    
    void clean(Queue<Integer> q, Map<Integer,Integer> map) {
        while (!q.isEmpty() && !map.containsKey(q.peek())) {
            q.poll();
        }
    }
}