import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();
		while(true) {
			int i = Integer.parseInt(st.nextToken());
			if (i == 0) break;
			list.add(i);
		}
		
		int N = list.size();
		
		// dp[i][l][r] : 0~i번째 지시까지 수행 시, 왼발위치 l, 오른발위치 r에서의 최소 코스트
		int[][][] dp = new int[N+1][5][5];
		for (int i=0; i<=N; i++) {
			for (int j=0; j<5; j++) {
				for (int k=0; k<5; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		dp[0][0][0] = 0;
		
		for (int i=0; i<N; i++) {
			int instruct = list.get(i);
			for (int l=0; l<5; l++) {
				for (int r=0; r<5; r++) {
					if (dp[i][l][r] == Integer.MAX_VALUE) {
						continue;
					}
					// 왼발 무빙
					if (instruct != r) {
						dp[i+1][instruct][r] = Math.min(dp[i+1][instruct][r], dp[i][l][r] + cost(l, instruct));	
					}
					
					// 오른발 무빙
					if (instruct != l) {
						dp[i+1][l][instruct] = Math.min(dp[i+1][l][instruct], dp[i][l][r] + cost(r, instruct));	
					}
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				answer = Math.min(answer, dp[N][i][j]);
			}
		}
		System.out.println(answer);
	}
	
	static int cost(int from, int to) {
		if (from == 0) {
			return 2;
		} else if (from == to) {
			return 1;
		} else if (Math.abs(from - to) == 2) {
			return 4;
		} else {
			return 3;
		}
	}
}