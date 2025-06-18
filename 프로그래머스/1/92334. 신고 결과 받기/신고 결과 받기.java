import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
    	
    	// 범인, 신고자
    	Map<String, Set<String>> reportMap = new HashMap<>();
    	for (String r : report) {
    		String[] split = r.split(" ");
    		String er = split[0];
    		String ee = split[1];
    		
    		Set<String> set = reportMap.getOrDefault(ee, new HashSet<>());
    		// 한 유저가 같은 유저를 여러 번 신고한 경우는 신고횟수 1회 처리
			set.add(er);
    		reportMap.put(ee, set);
    	}
    	
    	// 신고자, 받게 될 메일개수
    	Map<String, Integer> mailMap = new HashMap<>();
    	for (Map.Entry<String, Set<String>> entry : reportMap.entrySet()) {
    		if (entry.getValue().size() >= k) {
    			for (String elem : entry.getValue()) {
    				mailMap.put(elem, mailMap.getOrDefault(elem, 0) + 1);
    			}
    		}
    	}
    	
    	int[] answer = new int[id_list.length];
    	for (int i=0; i<id_list.length; i++) {
    		answer[i] = mailMap.getOrDefault(id_list[i], 0);
    	}
        return answer;
    }
}