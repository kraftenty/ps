import java.util.*;

class Node {
    String s;
    int l;
    public Node(String s, int l) {
        this.s=s;
        this.l=l;
    }
}

class Solution {

    public int solution(String begin, String target, String[] words) {
        int n = words.length;
        boolean[] visited = new boolean[n];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(begin, 0));

        int answer = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.s.equals(target)) {
                answer = cur.l;
                break;
            }

            for (int i=0; i<n; i++) {
                if (!visited[i] && diffOne(cur.s, words[i])) {
                    visited[i] = true;
                    q.offer(new Node(words[i], cur.l+1));
                }
            }
        }

        return answer;
    }

    boolean diffOne(String s1, String s2) {
        int diffCnt = 0;
        for (int i=0; i<s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
        }

        return diffCnt==1 ? true : false;
    }
}