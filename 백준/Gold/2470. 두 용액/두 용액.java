// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        Arrays.sort(arr);


        int l = 0;
        int r = N - 1;
        int resVal = (arr[l] + arr[r]);
        int resL = arr[l];
        int resR = arr[r];
        while (l < r) {
            int tmp = arr[l] + arr[r];
            if (Math.abs(tmp) < Math.abs(resVal)) {
                resVal = Math.abs(tmp);
                resL = arr[l];
                resR = arr[r];
            }

            if (tmp < 0) {
                l++;
            } else {
                r--;
            }

        }

        bw.write(resL + " " + resR);
        bw.flush();
    }

}