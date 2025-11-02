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
		
		Integer[] in = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(in, (a,b) -> {
			return b-a;
		});
	
		
		int[] arr = new int[M];
		for (int i=0; i<M; i++) {
			if (i < N) arr[i] = in[i];
		}
	
		// 파라메트릭 서치
		Arrays.sort(arr);
	
		int lt = 1;
		int rt = arr[M-1];
		while (lt <= rt) {
			int md = (lt+rt)/2; // 과자길이
			
			int cnt = 0; // 줄수있는 조카의 수
			for (int i=0; i<M; i++) {
				cnt += (arr[i]/md);
			}
			
			if (cnt<M) {
				rt = md - 1;
			} else {
				lt = md + 1;
			}
		}
		
		System.out.println(rt);
		
	}
	

	
}
	