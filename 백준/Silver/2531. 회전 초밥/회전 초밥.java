import java.io.*;
import java.util.*;


public class Main {
	
	static int N; // 접시의 수
	static int d; // 초밥의 가짓수
	static int k; // 연속해서 먹는 접시의 수
	static int c; // 쿠폰 번호

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// 회전이므로 벨트 확장
		int[] belt = new int[N + k];
		for (int i=0; i<N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		for (int i=0; i<k; i++) {
			belt[N+i] = belt[i]; 
		}
		
		

		// 초기 윈도우
		int maxVal = -1;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<k; i++) {
			map.put(belt[i], map.getOrDefault(belt[i], 0) + 1);
		}
		int initSize = map.size();
		if (!map.containsKey(c)) {
			initSize++;
		}
		maxVal = Math.max(maxVal, initSize);

		// 슬라이딩윈도우
		int lt = 1;
		for (int rt = k; rt < belt.length; rt++) {
			// 왼쪽 제거
			if (map.get(belt[lt-1]) == 1) {
				map.remove(belt[lt-1]);
			} else if (map.get(belt[lt-1]) > 1) {
				map.put(belt[lt-1], map.get(belt[lt-1]) - 1);
			}
			
			// 오른쪽 추가
			map.put(belt[rt], map.getOrDefault(belt[rt], 0) + 1);
			
			// 크기 재기
			int size = map.size();
			if (!map.containsKey(c)) {
				size++;
			}
//			System.out.println("size = " + size);
			maxVal = Math.max(maxVal, size);
			lt++;
		}
		
		
		System.out.println(maxVal);
		
	}
	
}