import java.util.*;

class Solution {
    
    final Map<Character, int[]> locMap = new HashMap<>();
    
    void initLoc() {
        locMap.put('U', new int[]{1, 0});
        locMap.put('D', new int[]{-1, 0});
        locMap.put('R', new int[]{0, 1});
        locMap.put('L', new int[]{0, -1});
    }
    
    boolean isValidMove(int y, int x) {
        if (y < -5 || y > 5 || x < -5 || x > 5) 
            return false;
        return true;
    }
    
    public int solution(String dirs) {
        initLoc();
        Set<String> answerSet = new HashSet<>();
        
        int y=0, x=0;
        for (char dir : dirs.toCharArray()) {
            int[] offset = locMap.get(dir);
            int ny = y + offset[0];
            int nx = x + offset[1];
            
            if (!isValidMove(ny, nx)) {
                continue;
            }
            
            answerSet.add(y + "," + x + "to" + ny + "," + nx);
            answerSet.add(ny + "," + nx + "to" + y + "," + x);
            
            y = ny;
            x = nx;
        }
        
        return answerSet.size() / 2;
    }
}