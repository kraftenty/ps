import java.io.*;
import java.util.*;


public class Main {
	
	static int N; // 이동하려고 하는 채널
	static int M; // 고장난 버튼의 개수
	static Set<Integer> noSet = new HashSet<>(); // 고장난 버튼들
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		if (M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				noSet.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 수빈이가 지금 보고 있는 채널은 100번이다.
		// 수빈이가 지금 이동하려고 하는 채널은 N이다.
		// 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지
		
		// 일단 시작점을 100부터 플/마 누르는횟수로시작
		int minCount = Math.abs(N - 100);
		
		// 시작점을 0부터 999999까지 돌려봄
		for (int i=0; i<=999999; i++) {
			if (possible(i)) {
				int cnt = Math.abs(N - i) + String.valueOf(i).length(); // 버튼누르는횟수 + 플/마누르는횟수
				minCount = Math.min(minCount, cnt);
			}
		}
		
		System.out.println(minCount);
	}
	
	static boolean possible(int n) {
		for (char c : String.valueOf(n).toCharArray()) {
			if (noSet.contains(c - '0')) {
				return false;
			}
		}
		return true;
	}
	
}