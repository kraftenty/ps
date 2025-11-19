class Solution {
    
    String[] words = {"aya", "ye", "woo", "ma"};
    
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String bab : babbling) {
            if (isValid(bab)) answer++;
        }
        return answer;
    }
    
    boolean isValid(String bab) {
        int idx = 0;
        String prev = "";
        
        while (idx < bab.length()) {
            boolean matched = false;
            
            for (String word : words) {
                if (bab.startsWith(word, idx) && !prev.equals(word)) {
                    idx += word.length();
                    prev = word;
                    matched = true;
                    break;
                }
            }
            
            if (!matched) {
                return false;                
            }
        }
        
        return true;
    }
}