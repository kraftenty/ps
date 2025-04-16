class Solution {
    // a = 마트에 줘야하는 빈 병 수
        // b = a개를 갖다주면 마트가 주는 콜라 병 수
        // n = 가지고 있는 빈 병의 개수
        static int totalReceivedCola = 0;

        public int solution(int a, int b, int n) {
            if (n < a) {
                return totalReceivedCola;
            }
            int receivedCola = (n / a) * b;
            totalReceivedCola += receivedCola;
            return solution(a, b, (n % a) + receivedCola);
        }
}