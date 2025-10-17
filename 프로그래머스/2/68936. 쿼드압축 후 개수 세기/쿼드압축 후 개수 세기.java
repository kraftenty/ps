class Solution {
    
    int[] answer;
    
    public int[] solution(int[][] arr) {
        answer = new int[] {0, 0};
        
        dfs(arr, 0, 0, arr.length);
        
        return answer;
    }
    
    void dfs(int[][] arr, int sy, int sx, int n) {
        if (n==1 || isAllSame(arr, sy, sx, n)) {
            answer[arr[sy][sx]]++;
            return;
        }
        
        // Z방향
        int half = n/2;
        dfs(arr, sy, sx, half); // 좌상
        dfs(arr, sy, sx+half, half); // 우상
        dfs(arr, sy+half, sx, half); // 좌하
        dfs(arr, sy+half, sx+half, half); // 우하
    }
    
    boolean isAllSame(int[][] arr, int sy, int sx, int n) {
        int first = arr[sy][sx];
        for (int y=sy; y<sy+n; y++) {
            for (int x=sx; x<sx+n; x++) {
                if (first != arr[y][x]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    

}