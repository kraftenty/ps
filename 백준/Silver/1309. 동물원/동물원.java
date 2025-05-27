import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] DP;
	static int N;
	
	final static int NONE = 0;
	final static int LEFT = 1;
	final static int RIGHT = 2;
	
	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		DP = new int[N+1][3];
		
		DP[1][NONE] = DP[1][LEFT] = DP[1][RIGHT] = 1;
		for (int i=2; i<=N; i++) {
			DP[i][NONE] = DP[i-1][NONE] + DP[i-1][LEFT] + DP[i-1][RIGHT];
			DP[i][LEFT] = DP[i-1][NONE] + DP[i-1][RIGHT];
			DP[i][RIGHT] = DP[i-1][NONE] + DP[i-1][LEFT];
			
			DP[i][NONE] %= 9901;
			DP[i][LEFT] %= 9901;
			DP[i][RIGHT] %= 9901;
		}
		
		int answer = DP[N][NONE] + DP[N][LEFT] + DP[N][RIGHT];
		System.out.println(answer % 9901);
	}

}