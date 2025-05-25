import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		int[][] arr = new int[N+1][5+1];
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1; j<=5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxIdx = 0; 
		int max = Integer.MIN_VALUE;
		for (int i=1; i<=N; i++) { // 학생
			int cnt = 0;
			for (int j=1; j<=N; j++) { // 비교학생
				for (int k=1; k<=5; k++) { // 반
					if (arr[i][k] == arr[j][k]) {
						cnt++;
						break;
					}
				}
			}
			if (cnt > max) {
				max = cnt;
				maxIdx = i;
			}
		}

		System.out.println(maxIdx);	
	}
}