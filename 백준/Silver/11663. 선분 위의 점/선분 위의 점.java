import java.io.*;
import java.util.*;

public class Main {
	
	static int N; // 점의 개수
	static int M; // 선분의 개수

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] points = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}
		// 점 배열 정렬
		Arrays.sort(points);
		
		// 입력으로 주어진 각각의 선분 마다, 선분 위에 입력으로 주어진 점이 몇 개 있는지 출력한다.
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			int count = upperBound(points, end) - lowerBound(points, start);
			sb.append(count).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int upperBound(int[] arr, int target) {
		int lt = 0;
		int rt = arr.length;
		while (lt < rt) {
			int md = (lt + rt) / 2;
			if (target < arr[md]) {
				rt = md;
			} else {
				lt = md + 1;
			}
		}
		
		return lt;
	}
	
	static int lowerBound(int[] arr, int target) {
		int lt = 0;
		int rt = arr.length;
		while (lt < rt) {
			int md = (lt + rt) / 2;
			if (target <= arr[md]) {
				rt = md;
			} else {
				lt = md + 1;
			}
		}
		
		return lt;
	}

}
