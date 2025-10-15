import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int waitIdx = 0;
        
        Map<Integer, Integer> onBridgeMap = new HashMap<>(); // 끝나는시간, 무게
        
        int time = 1;
        int curWeight = 0;
        while (waitIdx < truck_weights.length || !onBridgeMap.isEmpty()) {
            // 다리에서 내리기
            if (onBridgeMap.containsKey(time)) {
                curWeight -= onBridgeMap.get(time);
                onBridgeMap.remove(time);
            }
            
            // 다리에 싣기
            if (waitIdx < truck_weights.length && 
                onBridgeMap.size() < bridge_length && 
                (curWeight + truck_weights[waitIdx]) <= weight
            ) {
                onBridgeMap.put(time+bridge_length, truck_weights[waitIdx]);
                curWeight += truck_weights[waitIdx];
                waitIdx++;
            }
            
            
            time++;
        }
        
        return time-1;
    }
}