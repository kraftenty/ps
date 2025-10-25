class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            int rm = n%3;
            n/=3;
            
            if (rm == 0) {
                rm = 4;
                n--;
            }
            sb.insert(0, rm);
        }
        
        return sb.toString();
    }
}

// 9/3 = 3...0 -> 4
// 3