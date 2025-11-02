import java.util.*;
import java.io.*;


public class Main {
	
	static int M; // 조카의 수
	static int N; // 과자의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int maxLen = 0;
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxLen = Math.max(maxLen, arr[i]);
		}
		
	
		// 파라메트릭 서치
	
		int lt = 1;
		int rt = maxLen;
		int answer = 0;
		while (lt <= rt) {
			int md = (lt+rt)/2; // 과자길이
			
			int cnt = 0; // 줄수있는 조카의 수
			for (int i=0; i<N; i++) {
				cnt += (arr[i]/md);
			}
			
			if (cnt>=M) {
				answer = md;
				lt = md + 1;
			} else {
				rt = md - 1;
			}
		}
		
		System.out.println(answer);
		
	}
	

	
}
	