import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;

    static int find(int a) {
        if (parent[a] <= 0) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b, int d) {
        int rootA = find(a);
        int rootB = find(b);

        parent[rootA] += parent[rootB] - d;
        parent[rootB] = rootA;
    }

    static class road {
        int a;
        int b;
        int d;

        road(int a, int b, int d) {
            this.a = a;
            this.b = b;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        road[] roads = new road[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            roads[i] = new road(a, b, d);
        }

        Arrays.sort(roads, (o1, o2) -> o1.d - o2.d);
        int roadCnt = 0;
        int answer = 0;
        for (int i = 0; i < M; i++) {

            int rootA = find(roads[i].a);
            int rootB = find(roads[i].b);

            if (rootA != rootB) {
                union(rootA, rootB, roads[i].d);
                roadCnt++;
            }

            if (roadCnt == N - 1) {
                answer = -parent[rootA] - roads[i].d;
                break;
            }
        }

        System.out.println(answer);
    }
}
