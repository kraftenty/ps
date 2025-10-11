import java.util.*;

class Solution {
    int maxDepth;
    boolean found = false;
    List<String> path = new ArrayList<>();
    List<String> answer;
    Map<String, List<String>> map = new HashMap<>();

    public String[] solution(String[][] tickets) {
        maxDepth = tickets.length+1;
        for (String[] ticket : tickets) {
            if (map.get(ticket[0])==null) {
                map.put(ticket[0], new ArrayList<>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }

        for (List<String> li : map.values()) {
            Collections.sort(li);
        }

        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs("ICN", path);

        String[] ret = new String[maxDepth];
        for (int i=0; i<ret.length; i++) {
            ret[i] = answer.get(i);
        }

        return ret;

    }

    void dfs(String cur, List<String> path) {
        if (found) {
            return;
        }

        // 종료조건
        if (path.size() == maxDepth) {
            found = true;
            answer = new ArrayList(path);
            return;
        }

        List<String> nextLi = map.get(cur);
        if (nextLi == null) return;

        for (int i=0; i<nextLi.size(); i++) {
            String next = nextLi.get(i);

            // 티켓 사용
            nextLi.remove(i);
            path.add(next);

            dfs(next, path);

            // 복원
            path.remove(path.size()-1);
            nextLi.add(i, next);

            if (found) return;
        }

    }

}