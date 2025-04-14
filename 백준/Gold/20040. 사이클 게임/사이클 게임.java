// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int N; // 점 개수
    static int M; // 게임 로그 개수
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        // root(Union Find)배열 초기화
        root = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            String[] in2 = br.readLine().split(" ");
            int n1 = Integer.parseInt(in2[0]);
            int n2 = Integer.parseInt(in2[1]);

            if (find(n1) == find(n2)) { // 둘다 부모가 같으면 같은 집합
                result = i + 1;
                break;
            } else {
                union(n1, n2);
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    public static int find(int n) {
        // 루트가 자기자신이면
        if (root[n] == n) {
            return n;
        } else {
            return find(root[n]);
        }
    }

    public static void union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);
        root[n2] = n1;
    }

}