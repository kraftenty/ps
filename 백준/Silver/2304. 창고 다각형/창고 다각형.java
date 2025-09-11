import java.io.*;
import java.util.*;


public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int maxPtr = -1;
		int maxHeight = Integer.MIN_VALUE;
		int[] arr = new int[1001];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			if (H > maxHeight) {
				maxHeight = H;
				maxPtr = L;
			}
			arr[L] = H;
		}
		
		int sum = 0;
		int curHeight = -1;
		// 왼쪽
		for (int x=0; x<maxPtr; x++) {
			curHeight = Math.max(curHeight, arr[x]);
			sum += curHeight;
		}
		
		// 오른쪽
		curHeight = -1;
		for (int x=1000; x>maxPtr; x--) {
			curHeight = Math.max(curHeight, arr[x]);
			sum += curHeight;
		}
		
		// 가운데
		sum += maxHeight;
		
		System.out.println(sum);
	}

}