class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        
        int ptr1=0, ptr2=0;
        boolean success = true;
        for (String s : goal) {
            if (ptr1 < cards1.length && s.equals(cards1[ptr1])) {
                ptr1++;
            } else if (ptr2 < cards2.length && s.equals(cards2[ptr2])) {
                ptr2++;
            } else {
                success = false;
                break;
            }
        }
        
        return success ? "Yes" : "No";
    }
}