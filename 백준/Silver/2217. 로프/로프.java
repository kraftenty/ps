import java.util.*;
import java.io.*;


public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			int val = Integer.parseInt(br.readLine());
			arr[i] = val;
		}
		
		Arrays.sort(arr);
		
		int maxVal = 0;
		for (int i=0; i<N; i++) {
			maxVal = Math.max(maxVal, (N-i) * arr[i]);
		}
		System.out.println(maxVal);
	}
	
}