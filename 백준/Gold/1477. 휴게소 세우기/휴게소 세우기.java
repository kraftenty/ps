import java.util.*;
import java.io.*;


public class Main {
	
	static int N; // 현재 휴게소 개수
	static int M; // 더 지으려는 휴게소 개수
	static int L; // 고속도로 길이
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		
		int[] arr = new int[N+2]; // 휴게소위치
		arr[0] = 0;
		arr[N+1] = L;
		
		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
		}
		
		int[] gap = new int[N+1]; // 간격
		for (int i=0; i<N+1; i++) {
			gap[i] = arr[i+1]-arr[i];
		}
		
		// lt, rt, md : 간격
		int lt=1;
		int rt=L-1;
		int answer = 0;
		
		while (lt <= rt) {
			int md = (lt+rt)/2; // md = 간격
			int cnt=0; // 필요한 휴게소 개수
			for (int g : gap) {
				cnt += (g-1)/md;
			}
			
			if (cnt <= M) {
				// 가능. 간격 줄여보기
				answer = md;
				rt = md - 1;
			} else {
				// 간격 늘려야함
				lt = md + 1;
			}
		}
		
		
		System.out.println(answer);
	}

}
