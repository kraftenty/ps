import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 문제 개수
	static int M; // 먼저 풀면 좋은 규칙 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		int[] inDegree = new int[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A].add(B);
			inDegree[B] += 1;
		}
		
		Queue<Integer> q = new PriorityQueue<>();
		for (int i=1; i<=N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		// 위상정렬
		List<Integer> priority = new ArrayList<>();
		while(!q.isEmpty()) {
			int cur = q.poll();
			priority.add(cur);
			
			for (int next : graph[cur]) { 
				inDegree[next] --;
				if (inDegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		for (int i : priority) {
			System.out.print(i + " ");
		}
	}
}