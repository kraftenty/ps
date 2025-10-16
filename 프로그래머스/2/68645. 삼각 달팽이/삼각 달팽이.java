import java.util.*;

class Solution {
    
    int[] dy = new int[] {1, 0, -1};
    int[] dx = new int[] {0, 1, -1};
    
    int[][] board;
    
    int n;
    
    public List<Integer> solution(int n) {
        this.n = n;
        board = new int[n][n];
        for (int y=0; y<n; y++) {
            for (int x=n-1; x>y; x--) {
                board[y][x] = -1;
            }
        }
        
        int num = 1;
        int y = 0;
        int x = 0;
        int d = 0;
        while (true) {
            board[y][x] = num;
            
            boolean changed = false;
            for (int i=d; i<d+3; i++) {
                int nd = i%3;
                int ny = y + dy[nd];
                int nx = x + dx[nd];
                if (ny>=0 && ny<n && nx>=0 && nx<n && board[ny][nx] == 0) {
                    y = ny;
                    x = nx;
                    d = nd;
                    changed = true;
                    break;
                }
            }
            if (!changed) {
                break;
            }
            
            num++;
        }
        
        return getAnswer();
    }
    
    List<Integer> getAnswer() {
        List<Integer> answer = new ArrayList<>();
        for (int y=0; y<n; y++) {
            for (int x=0; x<y+1; x++) {
                answer.add(board[y][x]);
            }
        }
        return answer;
    }
}