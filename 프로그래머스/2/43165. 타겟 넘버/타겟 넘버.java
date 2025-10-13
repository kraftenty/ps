class Solution {
    
    int[] numbers;
    int target;
    int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0);
        
        return cnt;
    }
    
    void dfs(int depth, int value) {
        if (depth == numbers.length) {
            if (value == target) {
                cnt++;
            }
            return;
        }
        
        dfs(depth+1, value + numbers[depth]);
        dfs(depth+1, value - numbers[depth]);
    }
}