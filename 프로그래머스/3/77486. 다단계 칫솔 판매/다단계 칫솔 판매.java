import java.util.*;

class Solution {
	
	Map<String, String> referenceMap; // K가 V에게 추천받았다
	Map<String, Integer> moneyMap;    // K는 V만큼의 돈을 벌었다
	
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

    	referenceMap = new HashMap<>();
    	moneyMap = new HashMap<>();
    	
    	for (int i=0; i<enroll.length; i++) {
    		referenceMap.put(enroll[i], referral[i]);
    		moneyMap.put(enroll[i], 0);
    	}
    	moneyMap.put("-", 0); // center
    	
    	for (int i=0; i<seller.length; i++) {
    		String s = seller[i];
    		int m = amount[i];
    		share(s, m * 100);
    	}
    	
    	// 정답 배열 만들기
        int[] answer = new int[enroll.length];
        for (int i=0; i<enroll.length; i++) {
        	String s = enroll[i];
        	answer[i] = moneyMap.get(s);
        }
        return answer;
    }
    
    private void share(String me, int money) {
    	if (me.equals("-") || money < 1) {
    		return;
    	}
    	
    	int upperMoney = money / 10;
    	int myMoney = money - upperMoney;
    	moneyMap.put(me, moneyMap.get(me) + myMoney);
    	
    	share(referenceMap.get(me), upperMoney);
    }
}