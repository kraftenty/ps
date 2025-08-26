import java.io.*;
import java.util.*;

public class Main {

	static int H, W; // Height, Width
	static int R, C, D; // 아리스의 행,열,회전각
	
	static int[][] A; // 규칙표 A
	static int[][] B; // 규칙표 B
	static boolean[][] cleaned; // 먼지 제거 여부 (0, 1)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		A = new int[H][W];
		B = new int[H][W];
		cleaned = new boolean[H][W];
		
		for (int i=0; i<2; i++) {
			for (int j=0; j<H; j++) {
				String s = br.readLine();
				for (int k=0; k<W; k++) {
					if (i==0) {
						A[j][k] = s.charAt(k) - '0';
					} else {
						B[j][k] = s.charAt(k) - '0';
					}
				}
			}
		}
		
		
		// 시뮬레이션 루프
		int count = 0;
		int notClearCount = 0;	 		// 청소없이 이동한 횟수
		int clearCount = 0;				// 청소하며 이동한 횟수
		int[][][] state = new int[H][W][4]; // R,C에서 D방향으로 방문했을 때, 마지막으로 청소한 횟수
		for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(state[i][j], -1);
            }
        }
		
		while (true) {
			
			// 현재 칸에 먼지가 있다면 먼지를 제거합니다.
			// 방금 먼지를 제거했다면 규칙표 A를,
			// 먼지를 제거하지 않았다면 규칙표 B를 참조합니다. (이미 제거되어 있으므로 제거 안했다는뜻)
			if (!cleaned[R][C]) {
				cleaned[R][C] = true;
				D = (D + A[R][C]) % 4;
				clearCount++;
				notClearCount = 0;
			} else {
				D = (D + B[R][C]) % 4;
				if (state[R][C][D] == clearCount) { // 루프로 다시 돌아왔고, 하나도 청소된게 없을때
					break;
				}
				state[R][C][D] = clearCount;
				notClearCount++;
			}
			

			// 바라보는 방향으로 한 칸 전진합니다.
			int nr = R;
			int nc = C;
			switch (D) {
			case 0:
				nr--;
				break;
			case 1:
				nc++;
				break;
			case 2:
				nr++;
				break;
			case 3:
				nc--;
				break;
			}
			
			// 이동한 횟수 누적
			count++;	
			
			
			// 이동을 마친 후에, 로봇 청소기가 영역 밖으로 벗어났다면 작동을 중지합니다. 
			if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
				break;
			}

			
			R = nr;
			C = nc;
		}
		
		System.out.println(count - notClearCount);
	}

}
