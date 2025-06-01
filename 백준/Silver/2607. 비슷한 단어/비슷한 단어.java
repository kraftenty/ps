import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// S 입력
		String S = br.readLine();
		int[] sArr = new int[26];
		for (char c : S.toCharArray()) {
			sArr[c - 'A']++;
		}
		
		int answer = 0;
		for (int i=0; i<N-1; i++) {
			
			// T 입력
			String T = br.readLine();
			int[] tArr = new int[26];
			for (char c : T.toCharArray()) {
				tArr[c - 'A']++;
			}
			
			// S와 T 비교 
			int diff = 0;
			for (int j=0; j<26; j++) {
				diff += Math.abs(sArr[j] - tArr[j]);
			}
			
			if (diff <= 1 || (diff == 2 && S.length() == T.length())) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}