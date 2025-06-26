import java.util.*;

class Solution {
		
    class Edge implements Comparable<Edge> {
        public int start;
        public int end;
        public int weight; 

        public Edge(int start, int end, int weight) { 
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    int[] parent;

    public int solution(int n, int[][] costs) {

        List<Edge> edgeList = new ArrayList<>();
        for (int[] cost : costs) {
            edgeList.add(new Edge(cost[0], cost[1], cost[2]));
        }

        Collections.sort(edgeList);

        parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (Edge edge : edgeList) {
            // 같은 집합일 경우 패스
            if (find(edge.start) == find(edge.end)) {
                continue;
            }

            union(edge.start, edge.end);    		
            answer += edge.weight;
        }

        return answer;
    }

    int find(int q) {
        if (parent[q] == q) {
            return q;
        }

        return parent[q] = find(parent[q]);
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[b] = a;
    }
}