import java.util.*;

class Solution {
	
	private Map<String, Integer> courseMap; // 코스, 중복개수
	private Map<Integer, Integer> courseMaxCountMap; // 코스 길이, 최대 주문횟수
	
    public String[] solution(String[] orders, int[] course) {
    	courseMap = new HashMap<>();
    	courseMaxCountMap = new HashMap<>();
    	for (int c : course) {
    		courseMaxCountMap.put(c, 0);
    	}
    	
    	for (String order : orders) {
    		char[] orderArr = order.toCharArray();
    		Arrays.sort(orderArr);
    		combinations(0, orderArr, "");
    	}

    	
    	List<String> result = new ArrayList<>();
    	for (int len : course) {
    		// maxCount : len 길이를 가진 코스 중에서 최대 개수
    		int maxCount = courseMaxCountMap.get(len);
    		if (maxCount >= 2) {
    			for (Map.Entry<String, Integer> entry : courseMap.entrySet()) {
    				if (entry.getKey().length() == len && entry.getValue() == maxCount) {
    					result.add(entry.getKey());
    				}
    			}
    		}
    	}
    	
    	Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    // 조합 생성
    public void combinations(int idx, char[] arr, String s) {
    	if (s.length() > 0 && courseMaxCountMap.containsKey(s.length())) {
    		// 해당 조합의 주문횟수 업데이트
    		courseMap.put(s, courseMap.getOrDefault(s, 0) + 1);
    		// 해당 조합의 최대 주문 횟수 저장
    		courseMaxCountMap.put(s.length(), Math.max(courseMaxCountMap.get(s.length()), courseMap.get(s)));
    	}
    	
    	for (int i=idx; i<arr.length; i++) {
    		combinations(i+1, arr, s + arr[i]);
    	}
    }
}