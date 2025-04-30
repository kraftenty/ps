class Solution {
    public int solution(int n) {

        int count = 0;
        
        for (int i=1; i<=n; i++) {
            int sum = 0;
            for (int j=i; j<=(n+1); j++) {
                if (sum == n) {
                    count++;
                    break;
                } else if (sum < n) {
                    sum += j;
                } else {
                    break;
                }
            }
        }
        

        return count;
    }
}