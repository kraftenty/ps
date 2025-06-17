import java.util.*;

class Log {
	public String uid;
	public char stamp; // e: enter, l: leave
	
	public Log(String uid, char stamp) {
		this.uid = uid;
		this.stamp = stamp;
	}
}

class Solution {
    public String[] solution(String[] record) {
    	
    	// uid, 닉네임 맵
    	Map<String, String> nameMap = new HashMap<>();
    	
    	// 로그 기록용 리스트
    	List<Log> logList = new ArrayList<>();

    	int cnt = 0;
    	for (String r : record) {
    		String[] splited = r.split(" ");
    		if (splited[0].equals("Enter")) {
    			nameMap.put(splited[1], splited[2]);
    			logList.add(new Log(splited[1], 'e'));
    			cnt++;
    		} else if (splited[0].equals("Leave")) {
    			logList.add(new Log(splited[1], 'l'));
    			cnt++;
    		} else if (splited[0].equals("Change")) {
    			nameMap.put(splited[1], splited[2]);
    		}
    	}
    	
    	String[] answer = new String[cnt];
    	for (int i=0; i<cnt; i++) {
    		Log log = logList.get(i);
    		StringBuilder sb = new StringBuilder();
    		sb.append(nameMap.get(log.uid));
    		sb.append("님이 ");
    		if (log.stamp == 'e') {
    			sb.append("들어왔습니다.");
    		} else {
    			sb.append("나갔습니다.");
    		}
    		answer[i] = sb.toString();
    	}
        
        return answer;
    }
}