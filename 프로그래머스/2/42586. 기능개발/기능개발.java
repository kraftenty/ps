import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {

        int N = progresses.length;
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int remainingDays = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
            q.offer(remainingDays);
        }

        List<Integer> result = new ArrayList<>();
        int currentDay = q.poll();  // 첫 기능의 배포일 기준
        int count = 1;

        while (!q.isEmpty()) {
            int next = q.poll();
            if (next <= currentDay) {
                count++;  // 같은 날 배포 가능
            } else {
                result.add(count);  // 이전 그룹 종료
                currentDay = next;  // 새 기준일 설정
                count = 1;
            }
        }

        result.add(count);  // 마지막 그룹 추가
        return result;
    }
}