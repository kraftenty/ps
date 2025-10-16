class Solution {
    public long[] solution(long[] numbers) {
        int n = numbers.length;
        long[] answer = new long[n];
        
        for (int i=0; i<n; i++) {
            long x = numbers[i];
            if (x%2 == 0) { // 짝수
                answer[i] = x+1;
            } else { // 홀수
                long bit = 1;
                while ((x & bit) != 0) {
                    bit <<= 1;
                }
                
                x = x | bit; // 가장 오른쪽의 0을 1로 바꿈
                
                bit >>= 1;
                x = x & ~bit; // 가장 오른쪽의 0 바로 오른쪽의 비트를 0으로 바꿈
                
                answer[i] = x;
            }
        }
        return answer;
    }
}