class Solution {
    public int solution(int[][] lines) {
        int[] a = new int[203];
        
        for (int[] line : lines) {
            for (int i=line[0]; i<line[1]; i++) {
                a[i+100] ++;
            }
        }
        
        int answer = 0;
        for (int i=0; i<203; i++) {
            if (a[i] > 1) {
                answer++;
            }
        }
        return answer;
    }
    
    
}