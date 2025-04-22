// 백준

import java.io.*;
import java.util.*;

public class Main {
    static Set<Integer> coinSet;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동전의 개수
        int k = Integer.parseInt(st.nextToken()); // 원하는 동전가치의 합
        coinSet = new HashSet<>();
        dp = new int[k + 1];
        Arrays.fill(dp, -1);

        for (int i = 0; i < n; i++) {
            coinSet.add(Integer.parseInt(br.readLine()));
        }

        int res = func(k);
        if (res == 0x0fffffff) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    public static int func(int k) {
        if (k == 0)
            return 0;
        if (k < 0)
            return 0x0fffffff;

        int ret = dp[k];
        if (ret == -1) {
            ret = 0x0fffffff;
            for (int coin : coinSet) {
                ret = Math.min(ret, func(k - coin) + 1);
            }
        };
        return dp[k] = ret;
    }

}