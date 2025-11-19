class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        int[][] res = new int[n][n];
        for (int i=0; i<n; i++) {
            String bin = Integer.toBinaryString(arr1[i]);
            String padded = String.format("%" + n + "s", bin).replace(' ', '0');
            for (int j=0; j<n; j++) {
                res[i][j] = padded.charAt(j)-'0';
            }
        }
        
        for (int i=0; i<n; i++) {
            String bin = Integer.toBinaryString(arr2[i]);
            String padded = String.format("%" + n + "s", bin).replace(' ', '0');
            for (int j=0; j<n; j++) {
                if (padded.charAt(j) == '1')
                res[i][j] = 1;
            }
        }
        
        String[] answer = new String[n];
        for (int i=0; i<n; i++) {
            answer[i] = "";
            for (int j=0; j<n; j++) {
                answer[i] += res[i][j]==1 ? "#" : " ";
            }
        }
        return answer;
    }
}