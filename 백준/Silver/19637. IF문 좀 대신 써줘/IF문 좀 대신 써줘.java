import java.io.*;
import java.util.*;

public class Main {
	
	static class Duo {
		public String name;
		public int power;
		public Duo(String n, int p) {
			this.name = n;
			this.power = p;
		}
	}

	static int N; // 칭호 개수
	static int M; // 캐릭터 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 칭호는 오름차순으로 주어짐 -> 정렬 불필요
		Duo[] duos = new Duo[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int power = Integer.parseInt(st.nextToken());
			duos[i] = new Duo(name, power);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			int power = Integer.parseInt(br.readLine());
			int idx = binSearch(duos, power);
			sb.append(duos[idx].name).append("\n");
		}
		System.out.println(sb);
	}
	
	static int binSearch(Duo[] duos, int target) {
		int result = -1;
		int lt = 0;
		int rt = duos.length-1;
		
		while (lt <= rt) {
			int mid = (lt+rt)/2;
			if (target <= duos[mid].power) {
				result = mid;
				rt = mid - 1;
			} else {
				lt = mid + 1;
			}
		}
		
		return result;
	}
}