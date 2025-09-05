import java.io.*;
import java.util.*;


public class Main {
	
	static int N; // 사다리의 수
	static int M; // 뱀의 수
	static Map<Integer, Integer> ladder = new HashMap<>();
	static Map<Integer, Integer> snake = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ladder.put(x, y);
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			snake.put(u, v);
		}
		
		// bfs
		int[] dice = new int[101]; // 주사위 몇번 굴렸는지 카운트 + 방문체크용
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		while(!q.isEmpty()) {
			int now = q.poll();
			if (now == 100) {
				break;
			}
			// 주사위굴리기
			for (int d=1; d<=6; d++) {
				int next = 0;
				if (ladder.containsKey(now + d)) {      // 딱 갔더니 사다리가있으면
					next = ladder.get(now + d);
				} else if (snake.containsKey(now + d)) { // 딱 갔더니 뱀이 있으면
					next = snake.get(now + d);
				} else {                                 // 아무것도 없으면
					next = now + d;
				}
				
				if (next > 0 && next <= 100 && dice[next] == 0) {
					dice[next] = dice[now] + 1;
					q.offer(next);
				}
			}
		}
		
		System.out.println(dice[100]);

	}
}