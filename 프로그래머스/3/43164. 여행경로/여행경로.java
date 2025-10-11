import java.util.*;

class Solution {
    int ticketCnt;

    Map<String, List<String>> map = new HashMap<>();
    Map<String, boolean[]> visited = new HashMap<>();
    boolean found = false;

    List<String> answer;

    public String[] solution(String[][] tickets) {
        ticketCnt = tickets.length+1;
        for (String[] ticket : tickets) {
            if (map.get(ticket[0])==null) {
                map.put(ticket[0], new ArrayList<>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }

        for (String key : map.keySet()) {
            List<String> value = map.get(key);
            Collections.sort(value);
            visited.put(key, new boolean[value.size()]);

        }

        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs("ICN", path);


        return answer.toArray(new String[0]);
    }

    void dfs(String cur, List<String> path) {
        if (found) {
            return;
        }

        // 종료조건
        if (path.size() == ticketCnt) {
            found = true;
            answer = new ArrayList<>(path);
            return;
        }

        List<String> nextLi = map.get(cur);
        if (nextLi == null) return;
        boolean[] v = visited.get(cur);

        for (int i=0; i<nextLi.size(); i++) {
            if (v[i]) continue;

            // 티켓 사용
            v[i] = true;
            path.add(nextLi.get(i));

            dfs(nextLi.get(i), path);

            // 복원
            path.remove(path.size()-1);
            v[i] = false;
        }

    }

}