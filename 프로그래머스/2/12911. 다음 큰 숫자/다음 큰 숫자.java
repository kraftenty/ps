class Solution {
    public int solution(int n) {
        
        int answer = 0;
        
        int nOneCount = Integer.toString(n, 2).replace("0", "").length();
        
        for (int i=n+1; i<1000001; i++) {
            int iOneCount = Integer.toString(i, 2).replace("0", "").length();
            if (iOneCount == nOneCount) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}