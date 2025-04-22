// 백준

import java.io.*;
import java.util.*;

// A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다
class Node implements Comparable<Node>{
    public int to;
    public int weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

public class Main {
    // 프림 알고리즘 풀이

    static int V, E; // 정점 개수 V, 간선 개수 E
    static boolean[] visited; // 정점 방문 여부
    static Queue<Node> q; // 우선순위큐
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visited = new boolean[V + 1];
        q = new PriorityQueue<>();
        nodes = new List[V + 1];
        for (int i = 0; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            nodes[A].add(new Node(B, C));
            nodes[B].add(new Node(A, C));
        }

        int weightSum = 0;
        q.offer(new Node(1, 0));
        while (!q.isEmpty()) {
            Node curNode = q.poll();

            // 이미 방문한 노드라면 패스
            if (visited[curNode.to]) {
                continue;
            }

            visited[curNode.to] = true;
            weightSum += curNode.weight;
            for (Node nextNode : nodes[curNode.to]) {
                if (!visited[nextNode.to]) {
                    q.offer(nextNode);
                }
            }
        }

        System.out.println(weightSum);
    }


}