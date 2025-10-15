class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[] {-1};
        }
        
        
        
        int[] answer = new int[n];
        int quot = s / n;
        int remain = s % n;
        for (int i=n-1; i>=0; i--) {
            answer[i] = quot;
            if (remain > 0) {
                answer[i]++;
                remain--;
            }
        }
        return answer;
    }
}
// 