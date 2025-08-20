import java.io.*;
import java.util.*;

public class Main {

	static int N; // 명령의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Deque<Integer> dq = new ArrayDeque<>();
		
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			switch(cmd) {
			case 1:
				dq.addFirst(Integer.parseInt(st.nextToken()));
				break;
			case 2:
				dq.addLast(Integer.parseInt(st.nextToken()));
				break;
			case 3:
				if (!dq.isEmpty()) {
					sb.append(dq.pollFirst()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}
				break;
			case 4:
				if (!dq.isEmpty()) {
					sb.append(dq.pollLast()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}
				break;
			case 5:
				sb.append(dq.size()).append("\n");
				break;
			case 6:
				sb.append(dq.isEmpty() ? 1 : 0).append("\n");
				break;
			case 7:
				if (!dq.isEmpty()) {
					sb.append(dq.peekFirst()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}
				break;
			case 8:
				if (!dq.isEmpty()) {
					sb.append(dq.peekLast()).append("\n");
				} else {
					sb.append(-1).append("\n");
				}
				break;
			}
		}
		
		System.out.println(sb.toString());
		 
	}
}