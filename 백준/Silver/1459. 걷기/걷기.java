import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());
		long W = Long.parseLong(st.nextToken()); // 가로세로
		long S = Long.parseLong(st.nextToken()); // 대각선
		
		long answer = 0;
		
		long minXY = Math.min(X, Y);
		long remain = Math.max(X, Y) - minXY;
		
		// 대각선이 의미없음
		if (S >= 2*W) {
			answer = (X+Y) * W;
		}
		
		// 대각선이 무조건빠름
		else if (S < W) {
			answer += minXY*S;
			if (remain%2 == 0) {
				answer += remain*S;
			} else {
				answer += ((remain-1)*S + W);
			}
		}
		
		else {
			answer += minXY*S;
			answer += remain*W;
		}
		
		System.out.println(answer);
	}
	
}
	