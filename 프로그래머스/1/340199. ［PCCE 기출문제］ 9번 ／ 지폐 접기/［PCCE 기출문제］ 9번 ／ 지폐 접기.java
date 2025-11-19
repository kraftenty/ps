class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int walMin = Math.min(wallet[0], wallet[1]); // 15
        int walMax = Math.max(wallet[0], wallet[1]); // 30
        while (Math.min(bill[0], bill[1]) > walMin || Math.max(bill[0], bill[1]) > walMax) {
            if (bill[0] > bill[1]) {
                bill[0] /= 2;
            } else {
                bill[1] /= 2;
            }
            answer++;
        }
        return answer;
    }
}