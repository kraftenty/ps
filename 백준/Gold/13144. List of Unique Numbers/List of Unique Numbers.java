import java.util.*;
import java.io.*;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[] v = new boolean[100_001];
		
		long cnt = 0;
		int lt = 0;
		for (int rt=0; rt<N; rt++) {
			while (v[arr[rt]]) {
				v[arr[lt]] = false;
				lt++;
			}
			
			v[arr[rt]] = true;
			cnt += (rt - lt + 1);
		}
		
		System.out.println(cnt);
	}
	
}