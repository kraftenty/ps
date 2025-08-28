import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 볼의 총 개수

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();

		// 더 적은 개수의 볼 
		int redCount = s.replace("B", "").length();
		int blueCount = s.length() - redCount;
		int answer = Math.min(redCount, blueCount);
		
		// 앞에서부터 연속된 볼
		int frontContinuousCount = 0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(0) != s.charAt(i)) {
				break;
			}
			frontContinuousCount++;
		}
		if (s.charAt(0) == 'R') {
			answer = Math.min(answer, redCount - frontContinuousCount);
		} else {
			answer = Math.min(answer, blueCount - frontContinuousCount);
		}
		
		// 뒤에서부터 연속된 볼
		int backContinuousCount = 0;
		for (int i=s.length()-1; i>=0; i--) {
			if (s.charAt(s.length() - 1) != s.charAt(i)) {
				break;
			}
			backContinuousCount++;
		}
		if (s.charAt(s.length() - 1) == 'R') {
			answer = Math.min(answer, redCount - backContinuousCount);
		} else {
			answer = Math.min(answer, blueCount - backContinuousCount);
		}
		
		System.out.println(answer);
	}
}
// R BBB R B RRR      R이더적음 
// BB R BBBB R        R이더적음