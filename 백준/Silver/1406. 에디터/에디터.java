import java.io.*;
import java.util.*;

public class Main {
	
	static int M; // 명령어 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		Deque<Character> leftDq = new ArrayDeque<>();
		for (char c : s) {
			leftDq.offerLast(c);
		}
		Deque<Character> rightDq = new ArrayDeque<>();
				
		
		M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			
			if (cmd == 'L') {
				// 커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
				if (leftDq.isEmpty()) {
					continue;
				}
				rightDq.offerFirst(leftDq.pollLast());
			} else if (cmd == 'D') {
				// 커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
				if (rightDq.isEmpty()) {
					continue;
				}
				leftDq.offerLast(rightDq.pollFirst());
			} else if (cmd == 'B') {
				// 커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
				// 삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
				if (leftDq.isEmpty()) {
					continue;
				} 
				leftDq.pollLast();
			} else if (cmd == 'P') {
				// $라는 문자를 커서 왼쪽에 추가함
				char param = st.nextToken().charAt(0);
				leftDq.offerLast(param);
			}
			
//			System.out.println(leftDq + " " + rightDq);
//			System.out.print("----");
		}
		
		// 정답 출력
		StringBuilder sb = new StringBuilder();
		for (char c: leftDq) {
			sb.append(c);
		}
		for (char c : rightDq) {
			sb.append(c);
		}
		
		System.out.println(sb.toString());
		
	}

}