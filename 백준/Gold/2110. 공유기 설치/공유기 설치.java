import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int C;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int lt = 1;
		int rt = arr[N-1] - arr[0];
		int answer = -1;
		while (lt <= rt) {
			int mid = (lt+rt) / 2;
			if (able(mid)) {
				answer = mid;
				lt = mid + 1;
			} else {
				rt = mid - 1;
			}
		}
		
		System.out.println(answer);
		
	}
	
	public static boolean able(int capLength) {
		int count = 1;
		
		int now = arr[0];
		for (int i=1; i<N; i++) {
			if (arr[i] - now >= capLength) {
				count++;
				now = arr[i];
			}
		}
		
		return count >= C;
	}
}