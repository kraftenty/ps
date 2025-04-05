// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] board;

    static List<Node> house;
    static List<Node> chicken;
    static boolean[] chickenOpen;

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        board = new int[N][N];
        house = new ArrayList();
        chicken = new ArrayList();

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    house.add(new Node(i, j));
                } else if (board[i][j] == 2) {
                    chicken.add(new Node(i, j));
                }
            }
        }
        chickenOpen = new boolean[chicken.size()];

        dfs(0, 0);
        bw.write(String.valueOf(result));
        bw.flush();
    }

    static void dfs(int start, int cnt) {
        if (cnt == M) {
            int sum = 0;
            for (Node h : house) { // 집마다
                int tempMin = Integer.MAX_VALUE;
                for (int c = 0; c < chicken.size(); c++) { // 치킨집 거리비교 운행
                    if (chickenOpen[c]) {
                        tempMin = Math.min(tempMin, calcDistance(h, chicken.get(c)));
                    }
                }
                sum += tempMin;
            }
            result = Math.min(result, sum);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            chickenOpen[i] = true;
            dfs(i + 1, cnt + 1);
            chickenOpen[i] = false;
        }

    }

    static int calcDistance(Node house, Node chicken) {
        return Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
    }
}

class Node {
    int y;
    int x;

    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}