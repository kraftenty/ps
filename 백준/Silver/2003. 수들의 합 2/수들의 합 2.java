import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int lp = 0;
		int sum = 0;
		int cnt = 0;
		for (int rp =0; rp < N; rp++) {
			sum += arr[rp];
			if (sum == M) {
				cnt++;
			}
			
			while (sum >= M) {
				sum -= arr[lp++];
				if (sum == M)
					cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}

}