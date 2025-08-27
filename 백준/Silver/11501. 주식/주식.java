import java.io.*;
import java.util.*;

public class Main {
	
	static int T; // 테스트케이스 수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		
		for (int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine()); // 날의 수
			int[] price = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			
			// 투자 로직(그리디)
			long profit = 0;
			int maxPrice = price[N-1];
			for (int i=N-2; i>=0; i--) { // 마지막날부터 루프돔
				if (price[i] <= maxPrice) {
					profit += (maxPrice - price[i]);
				} else {
					maxPrice = price[i];
				}
			}
			
			answer.append(profit).append("\n");
		}
		
		// 정답 출력
		System.out.println(answer.toString());
	}

}
