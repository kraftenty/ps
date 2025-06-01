import java.util.*;

class Solution {
	    
    class Log {
        public String number;
        public int inTime;
        public int outTime;
        public boolean complete;

        // 입차
        public Log(String time, String number) {
            this.number = number;
            this.inTime = toMinute(time);
            this.outTime = toMinute("23:59");
            this.complete = false;
        }

        // 출차
        public void out(String time) {
            this.outTime = toMinute(time);
            this.complete = true;
        }

        public int toMinute(String s) {
            String[] li = s.split(":");
            int h = Integer.parseInt(li[0]);
            int m = Integer.parseInt(li[1]);
            return h*60 + m;
        }
    }

    public int[] solution(int[] fees, String[] records) {

        List<Log> logs = new ArrayList<>();

        // 입출차 기록 처리
        for (String record : records) {
            String[] li = record.split(" ");
            String time = li[0];
            String number = li[1];
            String stamp = li[2];
            if (stamp.equals("IN")) {
                Log log = new Log(time, number);
                logs.add(log);
            } else {
                for (Log log : logs) {
                    if (!log.complete && number.equals(log.number)) {
                        log.out(time);
                        break;
                    }
                }
            }
        }


        // 처리된 기록을 가지고 계산
        int baseMin = fees[0];
        int baseFee = fees[1];
        int addiMin = fees[2];
        int addiFee = fees[3];

        // TreeMap 사용 시 키값 오름차순대로 나옴
        Map<String, Integer> map = new TreeMap<>(); // 넘버, 분
        for (Log log : logs) {
            int min = log.outTime - log.inTime;
            map.put(log.number, map.getOrDefault(log.number, 0) + min);
        }

        int[] answer = new int[map.size()];
        int idx = 0;
        for (String s : map.keySet()) {
            int min = map.get(s);
            // 요금 계산
            int fee = baseFee;
            if (min > baseMin) {
                fee += (int) Math.ceil((double) (min - baseMin) / addiMin) * addiFee;
            }
            answer[idx] = fee;
            idx++;
        }

        return answer;
    }

}