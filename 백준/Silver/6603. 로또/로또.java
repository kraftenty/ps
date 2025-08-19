import java.io.*;
import java.util.*;

public class Main {
	
	static int k;
	static int[] S;
	static int[] combination;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 첫번째 수 k
			k = Integer.parseInt(st.nextToken());
			if (k == 0) {
				break;
			}
			
			// 집합 S에 포함되는 k개의 수 입력
			S = new int[k];
			for (int i=0; i<k; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			
			combination = new int[6];
			backtrack(0, 0);
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());	
	}
	
	static void backtrack(int start, int depth) {
		if (depth == 6) {
			for (int i=0; i<6; i++) {
				sb.append(combination[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=start; i<k; i++) {
			combination[depth] = S[i];
			backtrack(i+1, depth+1);
		}
	}

}
