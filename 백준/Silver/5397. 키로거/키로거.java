import java.util.*;
import java.io.*;

/**
 * 백스페이스: - (커서의 바로 앞에 글자 지움)
 * 화살표: <, > (커서의 위치 움직일 수 있음)
 */

public class Main {
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=0; t<T; t++) {
			List<Character> li = new LinkedList<>();
			String s = br.readLine();
			int idx = 0;
			for (char c : s.toCharArray()) {
				if (c == '<') {
					if (idx == 0) continue;
					idx--;
				} else if (c == '>') {
					if (idx == li.size()) continue;
					idx++;
				} else if (c == '-') {
					if (idx == 0) continue;
					li.remove(idx-1);
					idx--;
				} else { // 일반 글자
					li.add(idx, c);
					idx++;
				}
			}
			
			for (char c : li) {
				sb.append(c);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
//0 1 2 3 4
// B A P