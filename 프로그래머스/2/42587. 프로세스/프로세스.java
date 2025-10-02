import java.util.*;

class Process {
    int priority;
    boolean mark;
    public Process(int priority) {
        this.priority = priority;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        // 전처리(큐에 넣기)
        Queue<Process> q = new ArrayDeque<>();
        for (int i=0; i<priorities.length; i++) {
            Process p = new Process(priorities[i]);
            if (i == location) {
                p.mark = true;
            }
            q.offer(p);
        }
        
        Arrays.sort(priorities);
        int maxPtr = priorities.length-1;
        
        // 메인로직
        int answer = 0;
        while (!q.isEmpty()) {
            Process p = q.poll();
            if (p.priority >= priorities[maxPtr]) {
                answer++;
                if (p.mark) {
                    break;
                }
                
                if (p.priority == priorities[maxPtr]) {
                    maxPtr--;
                }
            } else {
                q.offer(p);
            }
        }
        return answer;
    }
}

// A B C D
// B C D A
// C D A B -> C 실행
// D A B -> D 실행
// A B -> A 실행
// B -> B 실행