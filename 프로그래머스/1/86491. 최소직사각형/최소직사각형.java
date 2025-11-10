class Solution {
    public int solution(int[][] sizes) {
        
        int maxWidth = -1;
        int maxHeight = -1;
        
        for (int[] size : sizes) {
            int a = size[0];
            int b = size[1];
            if (b>a) {
                maxWidth = Math.max(maxWidth, b);
                maxHeight = Math.max(maxHeight, a);
            } else {
                maxWidth = Math.max(maxWidth, a);
                maxHeight = Math.max(maxHeight, b);
            }
        }
        
        return maxWidth * maxHeight;
    }
}