import java.io.*;
import java.util.*;

public class Main {

	static class Brick implements Comparable<Brick> {
		public int area;
		public int height;
		public int weight;
		public int number;
		
		public Brick(int area, int height, int weight, int number) {
			this.area = area;
			this.height = height;
			this.weight = weight;
			this.number = number;
		}
		
		@Override
		public int compareTo(Brick o) {
			return o.area - this.area;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N]; // dp[n] = n번째 벽돌이 맨 위에 있을 때 탑의 최대 높이
		int[] trace = new int[N]; // 바로 밑 벽돌의 인덱스 추적 
		Arrays.fill(trace, -1);
		
		List<Brick> list = new ArrayList<>();
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int area = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.add(new Brick(area, height, weight, i+1));
		}
		
		Collections.sort(list);
		dp[0] = list.get(0).height;
		for (int i=1; i<N; i++) {
			int maxHeight = 0;
			for (int j=i-1; j>=0; j--) {
				if (list.get(j).weight > list.get(i).weight) {
					if (dp[j] > maxHeight) {
						maxHeight = dp[j];
						trace[i] = j;
					}
				}
			}
			dp[i] = maxHeight + list.get(i).height;
		}
		

		// 최대 높이 인덱스
		int maxIndex = 0;
		for (int i=0; i<N; i++) {
			if (dp[i] > dp[maxIndex]) {
				maxIndex = i;
			}
		}
		
		// 최대 높이 인덱스부터 쌓아온 벽돌번호 역추적
		Queue<Integer> q = new ArrayDeque<>();
		while (maxIndex != -1) {
			q.offer(list.get(maxIndex).number);
			maxIndex = trace[maxIndex];
		}
		
		System.out.println(q.size());
		while (!q.isEmpty()) {
			System.out.println(q.poll());
		}
		
	}
}

/**
 * 넓이 25		16		9		4		1
 * 높이 3		2		2		4		5
 * 무게 4		5		3		6		2
 * 번호 1		4		3		2		5
 * 
 */