import java.util.*;
import java.io.*;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i][0] = x;
			arr[i][1] = y;
		}
		
		Arrays.sort(arr, (a,b) -> a[0] - b[0]);
		
		Stack<Integer> st = new Stack<>();
		int cnt = 0;
		for (int i=0; i<N; i++) {
			while (!st.isEmpty() && st.peek() > arr[i][1]) {
				cnt++;
				st.pop();
			}
			
			if (!st.isEmpty() && st.peek() == arr[i][1]) {
				continue;
			}
			
			st.push(arr[i][1]);
		}

		while (!st.isEmpty()) {
			if (st.peek() != 0) cnt++;
			st.pop();
		}
		
		System.out.println(cnt);
		
	}
	
}