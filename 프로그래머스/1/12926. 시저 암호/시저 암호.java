class Solution {
    public String solution(String s, int n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append((char) ((c - 'A' + n) % 26 + 'A'));
                } else if (Character.isLowerCase(c)) {
                    sb.append((char) ((c - 'a' + n) % 26 + 'a'));
                } else {
                    // 공백
                    sb.append(c);
                }
            }
            return sb.toString();
        }
}