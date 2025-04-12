// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int T; // 테스트케이스 개수
    static int[] arr;
    static boolean[] visited;  // 방문 배열
    static boolean[] complete; // 팀이 만들어진 인원 마킹
    static int teammedCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            complete = new boolean[n + 1];
            teammedCount = 0;

            String[] s = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                arr[j] = Integer.parseInt(s[j-1]);

                // 1인 팀은 미리 처리해놓기
                if (j == arr[j]) {
                    visited[j] = true;
                    complete[j] = true;
                    teammedCount++;
                }
            }

            // dfs 수행
            for (int j = 1; j <= n; j++) {
                dfs(j);
            }
            bw.write((n - teammedCount) + "\n");
        }

        bw.flush();
    }

    public static void dfs(int now) {
        // 현재 학생 = now
        // 현재 학생이 지목한 다음학생 = next
        visited[now] = true;
        int next = arr[now];

        if (!visited[next]) { // 다음학생 방문 안했다면, dfs 타기
            dfs(next);
        } else if (!complete[next]) { // 다음학생을 방문하긴했는데 검사가 안되있다면, 사이클 발견
            teammedCount++;
            for (int idx = next; idx != now; idx = arr[idx]) {
                teammedCount++;
            }
        }

        // 한번 쭉 돈 다음에
        complete[now] = true; // 탐색 끝 마킹처리
    }

}