import java.util.*;
import java.io.*;

class Node {
	int y;
	int x;
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class Main {

	static int N;
	static int[][] A;
	
	final static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	final static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				A[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<int[]> movings = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			movings.add(new int[] {d-1, s});
		}
		
		// [0] 비바라기 첫 시전 시 구름 생성
		List<Node> clouds = new ArrayList<>();
		clouds.add(new Node(N-1, 0));
		clouds.add(new Node(N-1, 1));
		clouds.add(new Node(N-2, 0));
		clouds.add(new Node(N-2, 1));
		
		
		// 메인루프
		for (int[] moving : movings) {
			
			// [1] 모든 구름 이동
			moveCloud(clouds, moving);
			
			// [2] 각 구름에서 비가 내리기
			rain(clouds);
			
			// [3] 구름 사라지기
			// 구현 없음
			
			// [4] 물복사버그 마법 시전
			waterCopyMagic(clouds);
			
			// [5] 구름 생성
			List<Node> generatedClouds = generateCloud(clouds);
			clouds = generatedClouds;
			
		}
		
		System.out.println(getSumOfA());
		
	}
	
	static void moveCloud(List<Node> clouds, int[] moving) {
		int d = moving[0];
		int s = moving[1];
		
		for (Node cloud : clouds) {
			cloud.y = (cloud.y + (dy[d]*s % N) + N) % N;
			cloud.x = (cloud.x + (dx[d]*s % N) + N) % N;
		}
	}
	
	static void rain(List<Node> clouds) {
		for (Node cloud : clouds) {
			A[cloud.y][cloud.x]++;
		}
	}
	
	static void waterCopyMagic(List<Node> rainedNodes) {
		// 결과 배열 복사
		int[][] newA = new int[N][N];
		for (int y=0; y<N; y++) {
			newA[y] = Arrays.copyOf(A[y], N);
		}
		
		// 로직
		for (Node cloud : rainedNodes) {
			int sum = 0;
			for (int i=1; i<=7; i+=2) { // 대각선만
				int ny = cloud.y + dy[i];
				int nx = cloud.x + dx[i];
				if (ny>=0 && ny<N && nx>=0 && nx<N && A[ny][nx]>0) { 
					sum++;
				}
			}
			newA[cloud.y][cloud.x] += sum; 
		}
		
		// A에 결과 배열 덮어씌우기
		A = newA;
	}
	
	static List<Node> generateCloud(List<Node> removedClouds) {
		boolean[][] v = new boolean[N][N];
		for (Node c : removedClouds) {
			v[c.y][c.x] = true; 
		}
		
		List<Node> generatedClouds = new ArrayList<>();
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (!v[y][x] && A[y][x]>=2) {
					generatedClouds.add(new Node(y, x));
					A[y][x]-=2;
				}
			}
		}
		
		return generatedClouds;
	}
	
	static int getSumOfA() {
		int sum = 0;
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				sum += A[y][x];
			}
		}
		
		return sum;
	}
}
