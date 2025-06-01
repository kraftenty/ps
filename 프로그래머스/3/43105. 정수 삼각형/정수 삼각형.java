class Solution {
    public int solution(int[][] triangle) {
        // 밑에서부터 더해올라가기
        
        for (int i=triangle.length-2; i>=0; i--) { // 층수
            for (int j=0; j<triangle[i].length; j++) {
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        
        return triangle[0][0];
    }
}
/**
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5
**/