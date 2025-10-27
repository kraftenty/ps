import java.util.*;
import java.io.*;


public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Queue<Integer> lpq = new PriorityQueue<>(Collections.reverseOrder());
		Queue<Integer> rpq = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (lpq.isEmpty() || num <= lpq.peek()) {
				lpq.offer(num);
			} else {
				rpq.offer(num);
			}
			
			if (rpq.size() > lpq.size()) {
				lpq.offer(rpq.poll());
			} else if (lpq.size() >= rpq.size()+2) {
				rpq.offer(lpq.poll());
			}
			
			sb.append(lpq.peek()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
