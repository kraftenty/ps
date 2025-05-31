import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> list = getPrimeList(N);
		
		// 투포인터
		// rt가 선제적으로 움직이고, lt가 따라오는 방식
		int answer = 0;
		int sum = 0;
		int lt = 0;
		for (int rt = 0; rt < list.size(); rt++) {
			sum += list.get(rt);
			
			while (sum > N && lt <= rt) {
				sum -= list.get(lt);
				lt++;
			}
			
			if (sum == N) {
				answer++;
			}
		}
		
		System.out.println(answer);
		
	}
	
	// 에라토스테네스 체
	static List<Integer> getPrimeList(int n) {
		boolean[] prime = new boolean[n+1];
		Arrays.fill(prime, true);
		
		for (int i=2; i<=n; i++) {
			if (prime[i]) {
				for (int j=i*2; j<=n; j+=i) {
					prime[j] = false;
				}
			}
		}
		
		List<Integer> list = new ArrayList<>();
		for (int i=2; i<=n; i++) {
			if (prime[i]) {
				list.add(i);
			}
		}
		return list;
	}
}