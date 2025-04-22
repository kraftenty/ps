// 백준

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static double[][] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        points = new double[N+1][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
        }
        points[N][0] = points[0][0];
        points[N][1] = points[0][1];

        double answer = 0;
        for (int i = 0; i < N; i++) {
            answer += points[i][0] * points[i + 1][1] - points[i + 1][0] * points[i][1];
        }

        answer = 0.5 * Math.abs(answer);
        System.out.printf("%.1f\n", answer);
    }
}
