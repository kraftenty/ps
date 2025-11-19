import java.util.*;

class Solution {
    
    String[][] park;
    int R;
    int C;
    
    public int solution(int[] mats, String[][] park) {
        this.park = park;
        this.R = park.length;
        this.C = park[0].length;
        Integer[] ms = new Integer[mats.length];
        for (int i=0; i<mats.length; i++) {
            ms[i] = mats[i];
        }
        Arrays.sort(ms, Collections.reverseOrder());
        
        int answer = -1;
        for (int y=0; y<R; y++) {
            for (int x=0; x<C; x++) {
                if (!park[y][x].equals("-1")) continue;
                for (int mat : ms) {
                    if (mat <= answer) break;
                    if (check(mat, y, x)) {
                        answer = Math.max(answer, mat);
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
    
    boolean check(int mat, int sy, int sx) {
        if (sy+mat > R || sx+mat > C) return false;
        
        for (int y=sy; y<sy+mat; y++) {
            for (int x=sx; x<sx+mat; x++) {
                if (!park[y][x].equals("-1")) return false;
            }
        }
        
        return true;
    }
}