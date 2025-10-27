import java.util.*;

class Solution {
    
    Map<Integer, Map<String, Integer>> map = new HashMap<>();
    
    public int solution(int[][] points, int[][] routes) {
        // 무빙하기
        for (int[] route : routes) {
            moving(route, points);
        }
        
        int answer = 0;
        for (Map<String, Integer> m : map.values()) {
            for (int cnt : m.values()) {
                if (cnt > 1) answer++;
            }
        }

        return answer;
    }
    
    void moving(int[] route, int[][] points) {
        int sec = 0;
        
        int y = -1;
        int x = -1;
        for (int i=0; i<route.length-1; i++) {
            int[] startPoint = points[route[i]-1];
            int[] endPoint = points[route[i+1]-1];
            y = startPoint[0];
            x = startPoint[1];
            int ey = endPoint[0];
            int ex = endPoint[1];
            
            while (y!=ey || x!=ex) {
                recordToMap(y,x, sec);
                
                // y우선
                if (y != ey) {
                    y += (y-ey>0) ? -1 : 1;
                } else {
                    x += (x-ex>0) ? -1 : 1;
                }
                    
                sec++;
            }
        }
        
        recordToMap(y, x, sec);
    }
    
    void recordToMap(int y, int x, int sec) {
        String point = y+","+x;
        if (!map.containsKey(sec)) {
            map.put(sec, new HashMap<>());
        }
        map.get(sec).put(point, map.get(sec).getOrDefault(point, 0) + 1);
    }
}