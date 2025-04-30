class Solution {
     public int[] solution(String s) {

        int time = 0;  // 변환 횟수
        int count = 0; // 1의 개수

        while (!s.equals("1")) {
            int replacedLength = s.replace("0", "").length();
            count += s.length() - replacedLength;
            s = Integer.toString(replacedLength, 2);
            time++;
        }

        return new int[] {time, count};
    }
}