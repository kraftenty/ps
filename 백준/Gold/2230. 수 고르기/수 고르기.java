import java.util.*;
import java.io.*;


public class Main {

	static int N;
	static int M;
	
	static long[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int lt=0;
		long minGap = Long.MAX_VALUE;
		for (int rt=0; rt<N; rt++) {
			while (lt < rt && arr[rt] - arr[lt] >= M) {
				minGap = Math.min(minGap, arr[rt]-arr[lt]);
				lt++;
			}
		}
		
		System.out.println(minGap);
	}
}