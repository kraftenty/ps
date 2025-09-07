import java.io.*;
import java.util.*;

public class Main {

	static int N; // 컨베이어 벨트 한쪽면 길이 (총 2N)
	static int K; // 내구도가 0인 칸의 개수가 K 개 이상이라면 종료
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] A = new int[2*N + 1]; // 각 칸의 내구도
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=2*N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[] robot = new boolean[2*N + 1]; // 해당 칸에 로봇이 있는지
		
		int upPtr = 1;		// 올리는 위치
		int downPtr = N;	// 내리는 위치
		int cnt = 0;
		while (true) {
			cnt++;
			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			upPtr = (upPtr == 1) ? 2*N : upPtr - 1;
			downPtr = (downPtr == 1) ? 2*N : downPtr - 1;
			
			// downPtr에 로봇이 있으면 내림
			if (robot[downPtr]) {
				robot[downPtr] = false;
			}
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 
			// 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			for (int i=downPtr; i>downPtr-N+1; i--) {
				int targetPtr = i;
				if (targetPtr < 1) {
					targetPtr = 2*N - Math.abs(i);
				}
				int curPtr = targetPtr - 1;
				if (curPtr < 1) {
					curPtr = 2*N;
				}
				
				if (robot[curPtr] && A[targetPtr] > 0 && !robot[targetPtr]) {
					robot[curPtr] = false;
					A[targetPtr]--;
					if (targetPtr != downPtr) {
						robot[targetPtr] = true;
					}
				}
 			}
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (A[upPtr] != 0) {
				robot[upPtr] = true;
				A[upPtr]--;
			}
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			int zeroDurCnt = 0;
			for (int i=1; i<=2*N; i++) {
				if (A[i] == 0) {
					zeroDurCnt++;
				}
			}
			if (zeroDurCnt >= K) {
				break;
			}
			
		}
		
		System.out.println(cnt);
	}
	
}
