import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;

    static class item {
        int m;
        int v;

        item(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }

    static int[] bags;
    static item[] items;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bags = new int[K];
        items = new item[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i] = new item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        long answer = 0;

        Arrays.sort(bags);
        Arrays.sort(items, (o1, o2) -> {
            if (o1.m == o2.m) {
                return o2.v - o1.v;
            }
            return o1.m - o2.m;
        });

        int idx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < K; i++) {

            while (idx < N && items[idx].m <= bags[i]) {
                pq.add(items[idx].v);
                idx++;
            }
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
            if (idx > N) break;
        }
        System.out.println(answer);
    }
}
