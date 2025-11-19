import java.util.*;

class Solution {
    
    Map<String, Integer> map = new HashMap<>();
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);
        int extIdx = map.get(ext);
        int sortByIdx = map.get(sort_by);
        int ridCnt = 0;
        for (int i=0; i<data.length; i++) {
            if (data[i][extIdx]>=val_ext) {
                data[i][sortByIdx] = Integer.MAX_VALUE;
                ridCnt++;
            }
        }
        
        Arrays.sort(data, (a, b) -> a[sortByIdx] - b[sortByIdx]);
        
        int[][] answer = new int[data.length-ridCnt][4];
        for (int i=0; i<data.length-ridCnt; i++) {
            answer[i] = data[i];
        }
        return answer;
    }
}