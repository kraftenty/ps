class Solution {
    public String solution(String my_string) {
        boolean[] printed = new boolean[256];
        StringBuilder sb = new StringBuilder();
        for (char c : my_string.toCharArray()) {
            if (!printed[c]) {
                printed[c] = true;
                sb.append(c);
            }
        }
        return sb.toString();
    }
}