import java.util.*;
import java.io.*;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] lectures = new int[N][2];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			lectures[i][0] = s;
			lectures[i][1] = e;
		}
		
		Arrays.sort(lectures, (a,b) -> {
			return a[0]-b[0];
		});
		
		Queue<Integer> pq = new PriorityQueue<>();
		pq.offer(lectures[0][1]);
		
		for (int i=1; i<N; i++) {
			if (lectures[i][0] >= pq.peek()) {
				pq.poll();
			}
			pq.offer(lectures[i][1]);
		}
		
		System.out.println(pq.size());
	}
	
}