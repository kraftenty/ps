class Solution {
    
    int minVal;
    
    public int solution(int[] picks, String[] minerals) {
        minVal = 50*25;
        
        dfs(0, 0, picks, minerals);
        
        return minVal;
    }
    
    void dfs(int val, int idx, int[] picks, String[] minerals) {
        if (idx >= minerals.length || ((picks[0] + picks[1] + picks[2]) == 0)) {
            minVal = Math.min(minVal, val);
            return;
        }
        
        // 다곡
        if (picks[0] > 0) {
            picks[0]--;
            int end = Math.min(idx+5, minerals.length);
            int tempVal = val;
            for (int i=idx; i<end; i++) {
                tempVal += calc("diamond_pick", minerals[i]);
            }
            dfs(tempVal, idx+5, picks, minerals);
            picks[0]++;
        }
        // 철곡
        if (picks[1] > 0) {
            picks[1]--;
            int end = Math.min(idx+5, minerals.length);
            int tempVal = val;
            for (int i=idx; i<end; i++) {
                tempVal += calc("iron_pick", minerals[i]);
            }
            dfs(tempVal, idx+5, picks, minerals);
            picks[1]++;
        }
        
        // 돌곡
        if (picks[2] > 0) {
            picks[2]--;
            int end = Math.min(idx+5, minerals.length);
            int tempVal = val;
            for (int i=idx; i<end; i++) {
                tempVal += calc("stone_pick", minerals[i]);
            }
            dfs(tempVal, idx+5, picks, minerals);
            picks[2]++;
        }
    }
    
    int calc(String pick, String mineral) {
        if (pick.equals("diamond_pick")) {
            return 1;
        } else if (pick.equals("iron_pick")) {
            if (mineral.equals("diamond")) {
                return 5;
            } else {
                return 1;
            }
        } else {
            if (mineral.equals("diamond")) {
                return 25;
            } else if (mineral.equals("iron")) {
                return 5;
            } else {
                return 1;
            }
        }
    }
}