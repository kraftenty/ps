import java.util.*;
import java.io.*;


 
public class Main {

	static int N;
	static int[][] A;
	static int ty;
	static int tx;
	static int td;
	
	// 좌 하 우 상
	final static int[] dy = new int[] {0, 1, 0, -1};
	final static int[] dx = new int[] {-1, 0, 1, 0};
	
	
	final static double[][] sp = {
			{0, 0, 0.02, 0, 0},
			{0, 0.1, 0.07, 0.01, 0},
			{0.05, 99, 0, 0, 0},
			{0, 0.1, 0.07, 0.01, 0},
			{0, 0, 0.02, 0, 0}
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				A[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 토네이도 이동
		ty = N/2;
		tx = N/2;
		td = 0;
		int len = 1;
		int step = 0;
		int turnCnt = 0;
		int answer = 0;

		while (!(ty==0 && tx==0))  {
			ty += dy[td];
			tx += dx[td];
			step++;
			
			if (A[ty][tx]>0) {
				answer += spread();
			}
			
			if (len == step) {
				step = 0;
				turnCnt++;
				if (turnCnt==2) {
					turnCnt=0;
					len++;
				}
				td = (td + 1) % 4;
			}
			
		}
		
		System.out.println(answer);
		
	}
	
	
	static int spread() {
		int ret = 0;
		
		int c = td;
		double[][] s = sp;
		while (c-- > 0) {
			s = rotate(s);
		}
		
		int total = A[ty][tx];
		A[ty][tx] = 0;
		int spreadedAmount = 0;
		int alphaY=-1;
		int alphaX=-1;
		for (int y=0; y<5; y++) {
			for (int x=0; x<5; x++) {
				// 빈칸
				if (s[y][x]==0) continue;
				
				// 알파
				int ay = ty-2+y;
				int ax = tx-2+x;
				if (s[y][x]==99) {
					alphaY = ay;
					alphaX = ax;
					continue;
				}
				
				// 비율칸
				int amount = (int) (total * s[y][x]);
				spreadedAmount += amount;
				
				if (ay<0 || ay>=N || ax<0 || ax>=N) {
					// 밖으로 나가는거 세기
					ret += amount;
				} else {
					// 안으로 잘 쌓이는거
					A[ay][ax] += amount;
				}
				
			}
		}
		
		if (alphaY<0 || alphaY>=N || alphaX<0 || alphaX>=N) {
			ret += (total - spreadedAmount);
		} else {
			A[alphaY][alphaX] += (total - spreadedAmount);
		}
		
		return ret;
	}
	
	// 시계방향 90도회전
	static double[][] rotate (double[][] a) {
		double[][] result = new double[5][5];
		for (int y=0; y<5; y++) {
			for (int x=0; x<5; x++) {
				result[5-x-1][y] = a[y][x];
			}
		}
		return result;
	}
	
}