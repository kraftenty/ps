import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 가수의 수 (1~N번)
	static int M; // 보조PD의 수
	static List<Integer>[] list;
	static int[] inCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		inCount = new int[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int[] tmp = new int[size];
			for (int j=0; j<size; j++) {
				tmp[j] = Integer.parseInt(st.nextToken());
				if (j > 0) {
					list[tmp[j-1]].add(tmp[j]);
					inCount[tmp[j]] ++;
				}
			}
		}
		
		// 초기 세팅
		Queue<Integer> answer = new ArrayDeque<>();
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if (inCount[i] == 0) {
				q.add(i);
			}
		}
		
		// 위상정렬
		while(!q.isEmpty()) {
			int cur = q.poll();
			answer.offer(cur);
			for (int next : list[cur]) {
				inCount[next]--;
				if (inCount[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		boolean able = true;
		for (int i=1; i<=N; i++) {
			if (inCount[i] != 0) {
				able = false;
				break;
			}
		}
		
		if (!able) {
			System.out.println(0);
			return;
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(!answer.isEmpty()) {
			bw.write(answer.poll() + "\n");
		}
		bw.flush();

	}
}