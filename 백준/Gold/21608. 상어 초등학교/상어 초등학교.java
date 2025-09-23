import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	int y;
	int x;
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
	
	@Override
	public int compareTo(Node o) {
		if (this.y==o.y) {
			return this.x-o.x;
		}
		return this.y-o.y;
	}
}

public class Main {

	static int N;
	static int[][] board;
	static Map<Integer, Set<Integer>> likeMap;
	
	final static int[] dy = new int[] {-1, 1, 0, 0};
	final static int[] dx = new int[] {0, 0, -1, 1};
	
	// 1부터!!!!!!!!
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		likeMap = new HashMap<>();
		
		for (int i=0; i<N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			Set<Integer> likeSet = new HashSet<>();
			for (int j=0; j<4; j++) {
				likeSet.add(Integer.parseInt(st.nextToken()));
			}
			likeMap.put(num, likeSet);
			
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			List<Node> result1 = solution1(likeSet);
			if (result1.size() == 1) {
				Node n = result1.get(0);
				board[n.y][n.x] = num;
				continue;
			}
			
			// 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
			List<Node> result2 = solution2(result1);
			if (result2.size() == 1) {
				Node n = result2.get(0);
				board[n.y][n.x]= num;
				continue;
			}
			
			// 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로,
			// 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
			Collections.sort(result2);
			Node n = result2.get(0);
			board[n.y][n.x]= num;
		}
		
		int answer = getAnswer();
		System.out.println(answer);
	}
	

	static List<Node> solution1(Set<Integer> likeSet) {
		int[][] temp = new int[N+1][N+1];

		// 좋아하는 학생이 있는 인접한 칸의 개수의 최댓값 구하기
		int maxVal = Integer.MIN_VALUE;
		for (int y=1; y<=N; y++) {
			for (int x=1; x<=N; x++) {
				// 안비어있으면 패스
				if (board[y][x] != 0) {
					continue;
				}
				for (int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny>0 && ny<=N && nx>0 && nx<=N && likeSet.contains(board[ny][nx])) {
						temp[y][x]++;
					}
				}
				maxVal = Math.max(maxVal, temp[y][x]);
			}
		}
		
		// 최댓값이 있는 칸의 좌표를 리스트에 담아서 리턴
		List<Node> result = new ArrayList<>();
		for (int y=1; y<=N; y++) {
			for (int x=1; x<=N; x++) {
				if (board[y][x] != 0) {
					continue;
				}
				if (temp[y][x] == maxVal) {
					result.add(new Node(y, x));
				}
			}
		}
		return result;
	}
	
	static List<Node> solution2(List<Node> result1) {
		int[] adjEmptyCnt = new int[result1.size()];
		int maxEmptyCnt = Integer.MIN_VALUE;
		for (int i=0; i<result1.size(); i++) {
			Node c = result1.get(i);
			for (int d=0; d<4; d++) {
				int ny = c.y + dy[d];
				int nx = c.x + dx[d];
				if (ny>0 && ny<=N && nx>0 && nx<=N && board[ny][nx]==0) {
					adjEmptyCnt[i]++;
				}
			}
			maxEmptyCnt = Math.max(maxEmptyCnt, adjEmptyCnt[i]);
		}
		
		// 인접 칸 중에서 비어있는 칸이 가장 많은 칸 좌표를 리스트에 담아서 리턴
		List<Node> result = new ArrayList<>();
		for (int i=0; i<adjEmptyCnt.length; i++) {
			if (adjEmptyCnt[i] == maxEmptyCnt) {
				result.add(result1.get(i));
			}
		}
		
		return result;
	}
	
	static int getAnswer() {
		int answer = 0;
		for (int y=1; y<=N; y++) {
			for (int x=1; x<=N; x++) {
				Set<Integer> likeSet = likeMap.get(board[y][x]);
				int cnt = 0;
				for (int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny>0 && ny<=N && nx>0 && nx<=N && likeSet.contains(board[ny][nx])) {
						cnt++;
					}
				}
				
				if (cnt!=0) {
					answer += Math.pow(10, cnt-1);
				}
			}
		}
		
		return answer;
	}

}