import java.util.*;

class Edge implements Comparable<Edge> {
    int to, cost;
    public Edge (int to, int cost) { 
        this.to=to;
        this.cost=cost;
    }
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}


class Solution {
    
    ArrayList<Edge>[] graph;
    
    public int solution(int N, int[][] road, int K) {
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        
        // 다익스트라
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if (cur.cost > dist[cur.to]) continue;
            
            for (Edge next : graph[cur.to]) {
                int newCost = cur.cost + next.cost;
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.offer(new Edge(next.to, newCost));
                }
            }
        }
        
        int answer = 1;
        for (int i=2; i<=N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
}