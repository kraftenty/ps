// 백준

import java.io.*;
import java.util.*;

public class Main {
    static int N, K; // 보석 개수 N, 가방 개수 K
    static List<int[]> jewel; // 보석 (무게m, 가격v)
    static int[] C; // 각 가방의 최대 무게(Ci)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in1 = br.readLine().split(" ");
        N = Integer.parseInt(in1[0]);
        K = Integer.parseInt(in1[1]);

        jewel = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] in2 = br.readLine().split(" ");
            int M = Integer.parseInt(in2[0]); // 무게
            int V = Integer.parseInt(in2[1]); // 가격
            jewel.add(new int[]{M, V});
        }


        C = new int[K];
        for (int i = 0; i < K; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }

        // 보석: 무게기준 오름차순 정렬
        jewel.sort(Comparator.comparingInt(o -> o[0]));

        // 가방: Capacity 기준 오름차순 정렬
        Arrays.sort(C);

        // Capacity 작은 가방부터 하나씩 보면서
        // 현재 가방 무게 이하의 모든 보석을 고려 -> 가능한 보석들을 pq에 넣고,
        // 그중 가장 비싼 보석을 pq에서 뽑기
        Queue<Integer> pq = new PriorityQueue<>((a,b) -> b-a); // maxHeap
        long result = 0;
        int j=0;
        for (int i = 0; i < K; i++) {
            while (j < N && jewel.get(j)[0] <= C[i]) {
                pq.offer(jewel.get(j)[1]); // v 넣기
                j++;
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }
        bw.write(result + "\n");
        bw.flush();
    }

}