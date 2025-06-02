import java.util.*;
import java.io.*;

class Solution {

    class Node {
        int prev;
        int cur;
        int next;

        public Node(int prev, int cur, int next) {
            this.prev = prev;
            this.cur = cur;
            this.next = next;
        }
    }

    class Table {
        int n;
        int k;
        int[] prev;
        int[] next;
        boolean[] available;
        Stack<Node> deleteStack;


        public Table(int n, int k) {
            this.n = n;
            this.k = k;
            prev = new int[n];
            next = new int[n];
            available = new boolean[n];
            deleteStack = new Stack<>();

            Arrays.fill(available, true);

            // -1 : 이어지는 노드 없음 (양끝)
            for(int i=0; i<n; i++) {
                prev[i] = i-1;
                next[i] = i+1;
            }
            next[n-1] = -1;
        }

        public void U(int x) {
            while (x > 0) {
                k = prev[k];
                x--;
            }
        }

        public void D(int x) {
            while (x > 0) {
                k = next[k];
                x--;
            }
        }

        public void C() {
            // 삭제스택에 넣고 
            deleteStack.push(new Node(prev[k], k, next[k]));
            available[k] = false;
            // 현재 노드 삭제
            if (prev[k] != -1) {
                next[prev[k]] = next[k];
            }
            if (next[k] != -1) {
                prev[next[k]] = prev[k];
            }

            if (next[k] != -1) {
                k = next[k];
            } else {
                k = prev[k];
            }
        }

        public void Z() {
            Node node = deleteStack.pop();
            if (node.prev != -1) {
                next[node.prev] = node.cur;
            }
            if (node.next != -1) {
                prev[node.next] = node.cur;
            }
            available[node.cur] = true;
        }

        public String getState() {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<n; i++) {
                if (available[i]) {
                    sb.append("O");
                } else {
                    sb.append("X");
                }
            }
            return sb.toString();
        }
    }


    public String solution(int n, int k, String[] cmd) {

        Table table = new Table(n, k);

        for (String s : cmd) {
            StringTokenizer st = new StringTokenizer(s);
            String instruct = st.nextToken();

            if (instruct.equals("U")) {
                int x = Integer.parseInt(st.nextToken());
                table.U(x);
            } else if (instruct.equals("D")) {
                int x = Integer.parseInt(st.nextToken());
                table.D(x);
            } else if (instruct.equals("C")) {
                table.C();
            } else if (instruct.equals("Z")) {
                table.Z();
            }
        }

        return table.getState();
    }
}