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
		
		int[] answer = new int[N+1];
		
		for (int i=1; i<=N; i++) { // 학생
			boolean[] visited = new boolean[N+1];
			for (int j=1; j<=5; j++) { // 학년
				int clazz = arr[i][j];
				for (int k=1; k<=N; k++) { // 비교학생
					if (k != i && arr[k][j] == clazz) {
						visited[k] = true;
					}
				}
			}
			for (int l=1; l<=N; l++) {
				if (visited[l]) answer[i]++;
			}
		}
		
		int max = Integer.MIN_VALUE;
		int maxIdx = -1;
		for (int i=1; i<=N; i++) {
			if (answer[i] > max) {
				max = answer[i];
				maxIdx = i;
			}
		}
		
		System.out.println(maxIdx);
		
		
	}
}