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
		
		int maxVal = 0;
		for (int i=0; i<N; i++) {
			int cnt = 0;
			double pivSlope = 0;
			
			// 왼쪽
			for (int lt=i-1; lt>=0; lt--) {
				double slope = (double) (arr[i] - arr[lt]) / (i - lt);
				if (lt == i-1 || pivSlope > slope) {
					pivSlope = slope;
					cnt++;
				}
			}

			// 오른쪽
			for (int rt=i+1; rt<N; rt++) {
				double slope = (double) (arr[rt]-arr[i]) / (rt - i);
				if (rt==i+1 || pivSlope < slope) {
					pivSlope = slope;
					cnt++;
				}
			}

			maxVal = Math.max(maxVal, cnt);
		}
		
		System.out.println(maxVal);
	}
	

	
}
	