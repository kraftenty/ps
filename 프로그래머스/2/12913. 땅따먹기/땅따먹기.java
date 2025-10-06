class Solution {
    int solution(int[][] land) {

        int R = land.length;
        int C = land[0].length;

        for (int y=1; y<R; y++) {
            for (int x=0; x<C; x++) {
                
                int maxVal = Integer.MIN_VALUE;
                for (int px=0; px<C; px++) { // px: 한 행 전 x
                    if (px==x) continue;
                    
                    maxVal = Math.max(maxVal, land[y-1][px] + land[y][x]);
                }
                
                land[y][x] = maxVal;
            }
        }
        
        int answer = 0;
        for (int x=0; x<C; x++) {
            answer = Math.max(answer, land[R-1][x]);
        }
        return answer;
    }
}