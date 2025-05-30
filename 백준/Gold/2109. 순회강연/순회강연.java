import java.util.*;
import java.io.*;

public class Main {
	
	static class Lecture implements Comparable<Lecture> {
		public int cost;
		public int deadline;
		
		public Lecture(int c, int d) {
			this.cost = c;
			this.deadline = d;
		}
		
		@Override
		public int compareTo(Lecture o) {
			return o.deadline - this.deadline;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Lecture> list = new ArrayList<>();
		
		int max = -1;
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int deadline = Integer.parseInt(st.nextToken());
			list.add(new Lecture(cost, deadline));
			max = Math.max(max, deadline);
		}
		Collections.sort(list);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int idx = 0;
		int answer = 0;
		for (int day = max; day>0; day--) {
			while (idx < N && list.get(idx).deadline >= day) {
				pq.offer(list.get(idx).cost);
				idx++;
			}
			
			if (!pq.isEmpty()) {
				answer += pq.poll();	
			}
		}
		
		System.out.println(answer);
	}
	
}