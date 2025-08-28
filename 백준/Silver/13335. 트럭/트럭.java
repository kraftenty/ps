import java.io.*;
import java.util.*;

class Truck {
	int weight;
	int exitTime;
	public Truck(int weight, int exitTime) {
		this.weight = weight;		// 트럭의 무게
		this.exitTime = exitTime; 	// 트럭이 다리를 빠져나가는 시간
	}
}

public class Main {
	
	static int n;	// 트럭의 개수
	static int w;	// 다리의 길이
	static int L;	// 다리의 최대하중

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Deque<Integer> waitingDq = new ArrayDeque<>();
		for (int i=0; i<n; i++) {
			waitingDq.addLast(Integer.parseInt(st.nextToken()));
		}
		Deque<Truck> bridgeDq = new ArrayDeque<>();
		
		int time = 0;
		int load = 0; // 현재 다리 하중
		while(!waitingDq.isEmpty() || !bridgeDq.isEmpty()) {
			time++;
			// 다리에서 트럭 빼내기
			if (!bridgeDq.isEmpty() && bridgeDq.peekFirst().exitTime <= time) {
				Truck exitTruck = bridgeDq.pollFirst();
				load -= exitTruck.weight;
			}
			
			// 다리에 트럭을 올릴 수 있는 경우
			if (!waitingDq.isEmpty() && ((load + waitingDq.peekFirst()) <= L)) {
				Truck truck = new Truck(waitingDq.pollFirst(), time + w);
				load += truck.weight;
				bridgeDq.addLast(truck);
			}
		}
		
		System.out.println(time);
	}
	

}
