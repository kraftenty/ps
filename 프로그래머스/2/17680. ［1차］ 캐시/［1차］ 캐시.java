import java.util.*;

class Solution {
    
    Queue<String> q = new ArrayDeque<>();
    
    public int solution(int cacheSize, String[] cities) {
        
        if (cacheSize == 0) {
            return cities.length*5;
        }
        
        int answer = 0;
        
        for (String city : cities) {
            city = city.toLowerCase();
            
            if (q.contains(city)) { // hit
                q.remove(city);
                q.offer(city);
                answer++;
            } else { // miss
                if (q.size() == cacheSize) {
                    q.poll();
                }
                q.offer(city);
                answer+=5;
            }
           
        }
        
        
        return answer;
    }
}