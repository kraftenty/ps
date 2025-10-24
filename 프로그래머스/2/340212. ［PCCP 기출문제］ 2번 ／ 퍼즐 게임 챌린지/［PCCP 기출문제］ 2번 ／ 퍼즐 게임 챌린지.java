class Solution {
    public int solution(int[] diffs, int[] times, long limit) {

        // 이분탐색
        int lt = 1;
        int rt = 100000;
        int answer = rt;
        
        while (lt <= rt) {
            int md = (lt + rt) / 2;
            long res = calc(md, diffs, times);
            
            if (res > limit) {
                lt = md + 1;
            } else {
                answer = md;
                rt = md - 1;
            }
        }
        
        return answer;
    }
    
    // 메인로직
    long calc(int level, int[] diffs, int[] times) {
        long sum = times[0]; // 첫번째 퍼즐은 난이도 1 고정
        for (int i=1; i<diffs.length; i++) {
            if (diffs[i] > level) {
                sum += (diffs[i] - level) * (times[i] + times[i-1]);
            }
            sum += times[i];
        }
        
        return sum;
    }
}
