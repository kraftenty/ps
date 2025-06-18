import java.util.*;

class Solution {
	
	private Map<String, Integer> courseMap; // 코스, 중복개수
	private Set<Integer> courseLengthSet; // 코스 길이 셋
	
    public String[] solution(String[] orders, int[] course) {
    	courseMap = new HashMap<>();
    	courseLengthSet = new HashSet<>();
    	for (int c : course) {
    		courseLengthSet.add(c);
    	}
    	
    	for (String order : orders) {
    		char[] orderArr = order.toCharArray();
    		Arrays.sort(orderArr);
    		combinations(0, orderArr, "");
    	}

    	
    	List<String> result = new ArrayList<>();
    	for (int len : courseLengthSet) {
    		// maxCount : len 길이를 가진 코스 중에서 최대 개수
    		int maxCount = 0;
    		for (Map.Entry<String, Integer> entry : courseMap.entrySet()) {
    			if (entry.getKey().length() == len && entry.getValue() >= 2) {
    				maxCount = Math.max(maxCount, entry.getValue());
    			}
    		}
    		
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
    	if (s.length() > 0 && courseLengthSet.contains(s.length())) {
    		courseMap.put(s, courseMap.getOrDefault(s, 0) + 1);
    	}
    	
    	for (int i=idx; i<arr.length; i++) {
    		combinations(i+1, arr, s + arr[i]);
    	}
    }
}