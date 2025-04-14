// 백준

import java.io.*;
import java.util.*;

public class Main {
    // K개를 더해서 그 합이 N이 되는 '경우의 수' => dp[K][N]
    final static long MOD = 1000000000;
    static int N;
    static int K;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        dp = new long[K+1][N+1];

        // 1개를 더해서 그 합이 n이 되는 경우의수 = 각 1개씩
        for (int n = 0; n <= N; n++) {
            dp[1][n] = 1;
        }

        for (int k = 1; k <= K; k++) { // 더하는 수 개수
            for (int n = 0; n <= N; n++) { // 다 더한 합이 n이 됨
                // dp[k][n]의 경우의 수 모으기
                for (int l = 0; l <= n; l++) { // 마지막으로 더하는 수 l
                    dp[k][n] += (dp[k - 1][n - l] % MOD);
                }
            }
        }

        System.out.println(dp[K][N] % MOD);
    }

}