import java.util.*;
import java.io.*;

public class Main {
    static int N;
	static int[][] A;
	static int[][] D;
	
	final static int RED = 0;
	final static int GREEN = 1;
	final static int BLUE = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N][3];
		D = new int[N][3];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i][RED] = Integer.parseInt(st.nextToken());
			A[i][GREEN] = Integer.parseInt(st.nextToken());
			A[i][BLUE] = Integer.parseInt(st.nextToken());
		}
		
		D[0][RED] = A[0][RED];
		D[0][GREEN] = A[0][GREEN];
		D[0][BLUE] = A[0][BLUE];
		
		int minCost = Math.min(getCost(N-1, RED), Math.min(getCost(N-1, GREEN), getCost(N-1, BLUE)));
		System.out.println(minCost);
	}
	
	static int getCost (int n, int color) {
		if (D[n][color] == 0) {
			if (color == RED) {
				D[n][RED] = Math.min(getCost(n-1, GREEN), getCost(n-1, BLUE)) + A[n][RED]; 
			} else if (color == GREEN) {
				D[n][GREEN] = Math.min(getCost(n-1, RED), getCost(n-1, BLUE)) + A[n][GREEN];
			} else {
				D[n][BLUE] = Math.min(getCost(n-1, RED), getCost(n-1, GREEN)) + A[n][BLUE];
			}
		}
		
		return D[n][color];
	}

}