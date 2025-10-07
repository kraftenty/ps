class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] board = new int[m+1][n+1];
        board[1][1] = 1;
        
        for (int i=0; i<puddles.length; i++) {
            board[puddles[i][0]][puddles[i][1]] = -1;
        }
        
        for (int y=1; y<=m; y++) {
            for (int x=1; x<=n; x++) {
                // 물웅덩이면 스킵
                if(board[y][x]==-1) {
                    continue;
                }
                
                // 위에서 왔음
                if (y > 0 && board[y-1][x] != -1) {
                    board[y][x] = (board[y][x] + board[y-1][x]) % 1_000_000_007;
                }
                
                // 왼쪽에서 왔음
                if (x > 0 && board[y][x-1] != -1) {
                    board[y][x] = (board[y][x] + board[y][x-1]) % 1_000_000_007;
                }
                
            }
        }
        
        
        return board[m][n] % 1_000_000_007;
    }
}