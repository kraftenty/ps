import java.util.*;
import java.io.*;


public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		int maxDist = Integer.MIN_VALUE;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph[cur]) {
				int newDist = dist[cur]+1;
				if (newDist < dist[next]) {
					dist[next] = newDist;
					maxDist = Math.max(maxDist, newDist);
					q.offer(next);
				}
			}
		}
		
		int cnt = 0;
		int firstIdx = -1;
		for (int i=1; i<=N; i++) {
			if (dist[i]==maxDist) {
				cnt++;
				if (firstIdx==-1) {
					firstIdx = i;
				}
			}
		} 
		
		
		System.out.println(firstIdx + " " + dist[firstIdx] + " " + cnt);
	}
}
	