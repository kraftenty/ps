// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 수열의 크기
    static int[] arr; // 칠판에 적은 수열

    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        dp = new boolean[N][N];
        setDp(); // dp 배열 세팅


        int M = Integer.parseInt(br.readLine()); // 테스트케이스
        for (int i = 0; i < M; i++) {
            String[] inp = br.readLine().split(" ");
            int start = Integer.parseInt(inp[0]);
            int end = Integer.parseInt(inp[1]);
            if (dp[start-1][end-1]) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();
    }

    public static void setDp() {
        // 연속된 숫자가 1개일 경우 (자기자신) 세팅
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        // 연속된 숫자가 2개일 경우 세팅
        for (int i = 0; i < N - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i+1] = true;
            }
        }

        // 연속된 숫자가 3개 이상일 경우 세팅
        // dp[i][j] = (arr[i] == arr[j] && dp[i+1][j-1] == true)
        for (int s = 2; s <= N; s++) {
            for (int i = 0; i < N - s; i++) {
                int j = i + s;
                dp[i][j] = (arr[i] == arr[j] && dp[i + 1][j - 1]);
            }
        }

    }

}