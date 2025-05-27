import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<>();
		List<Integer> killed = new ArrayList<>();
		for (int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		int num = 1;
		while (q.size() > 0) {
			int now = q.poll();
			if (num != K) {
				q.offer(now);
			} else {
				killed.add(now);
			}
			num++;
			if (num > K) num = 1;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write("<");
		for (int i=0; i<(N-1); i++) {
			bw.write(killed.get(i) + ", ");
		}
		bw.write(killed.get(N-1) + ">");
		bw.flush();
	}
}