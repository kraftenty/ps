// 백준
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 2][10];
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= (N+1); i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % 10007;
                }
            }
        }

        System.out.print(dp[N+1][0] % 10007);

    }
}