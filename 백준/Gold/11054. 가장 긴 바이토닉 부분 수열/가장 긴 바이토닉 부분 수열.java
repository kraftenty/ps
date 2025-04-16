// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 수열 A의 크기
    static int[] A; // 수열

    static int[] dp_asc; // 증가하는 부분수열 dp
    static int[] dp_desc; // 감소하는 부분수열 dp


    // 부분수열: 떨어져있어도 순서만 맞으면 상관없음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();
        dp_asc = new int[N];
        dp_desc = new int[N];
        // 수열의 길이는 1부터 시작이므로, 전체를 1로 초기화
        for (int i = 0; i < N; i++) {
            dp_asc[i] = 1;
            dp_desc[i] = 1;
        }


        // 가장 긴 증가하는 부분수열의 길이 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    dp_asc[i] = Math.max(dp_asc[i], dp_asc[j] + 1);
                }
            }
        }

        // 가장 긴 감소하는 부분수열의 길이 구하기
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N-1; j > i; j--) {
                if (A[j] < A[i]) {
                    dp_desc[i] = Math.max(dp_desc[i], dp_desc[j] + 1);
                }
            }
        }




        int maxLenVal = 0;
        for (int i = 0; i < N; i++) {
            maxLenVal = Math.max(maxLenVal, (dp_asc[i] + dp_desc[i] - 1));
        }
        bw.write(maxLenVal + "\n");
        bw.flush();
        // 1 5 2 1 4 3 4 5 2 1
        // 1 1 2 2 2 3 4 5 2 1
        // v   v     v v v v v 7개
        // 어디까지 증가할 것인가 (꺾이는 부분 알아내기)
    }


}
