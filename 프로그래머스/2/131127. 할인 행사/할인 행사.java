import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
    	
    	// 초기 10개 제품을 맵에 담음
    	Map<String, Integer> discountMap = new HashMap<>();
    	for (int i=0; i<10; i++) {
    		discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
    	}
    	
    	// 원하는 제품도 맵에 담음
    	Map<String, Integer> wantMap = new HashMap<>();
    	for (int i=0; i<want.length; i++) {
    		wantMap.put(want[i], number[i]);
    	}
    	
    	
    	// 검사하고, 맨처음 제품 빼고 맨끝 제품 담기
    	int answer = 0;
    	for (int i=10; i<discount.length; i++) {
    		// 검사
    		if (wantMap.equals(discountMap)) {
    			answer++;
    		}

    		// 맨처음 제품 빼고
    		discountMap.put(discount[i-10], discountMap.get(discount[i-10])-1);
    		if (discountMap.get(discount[i-10]) == 0) {
    			discountMap.remove(discount[i-10]);
    		}
    		// 맨끝 제품 담기
    		discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
    	}
    	
    	// 마지막 검사
    	if (wantMap.equals(discountMap)) {
			answer++;
		}
    	

        return answer;
    }
}
