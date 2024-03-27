import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static boolean[] truth;
    static ArrayList[] party;

    static int find(int a) {
        if (parent[a] <= 0) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        if (p1 == p2) return;
        if (truth[p1] || truth[p2]) {
            truth[p1] = truth[p2] = true;
        }
        parent[p1] += parent[p2];
        parent[p2] = parent[b] = p1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        int truthCnt = Integer.parseInt(st.nextToken());
        if (truthCnt == 0) {
            System.out.println(M);
            return;
        }
        truth = new boolean[N + 1];
        party = new ArrayList[M];

        for (int i = 0; i < truthCnt; i++) {
            truth[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pplCnt = Integer.parseInt(st.nextToken());
            party[i] = new ArrayList<Integer>(pplCnt);
            int first = Integer.parseInt(st.nextToken());
            int firstP = find(first);
            party[i].add(firstP);
            for (int j = 1; j < pplCnt; j++) {
                int parent = find(Integer.parseInt(st.nextToken()));
                party[i].add(parent);
                union(firstP, parent);
            }
        }
        int answer = 0;
        for (int i = 0; i < M; i++) {
            boolean nobodyKnow = true;
            for (Object num : party[i]) {
                if (truth[find((int) num)]) {
                    nobodyKnow = false;
                    break;
                }
            }
            if (nobodyKnow) answer++;
        }
        System.out.println(answer);
    }
}
