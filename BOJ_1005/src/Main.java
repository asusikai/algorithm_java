import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, W;
    static int[] build;
    static ArrayList[] order;
    static int[] time;
    static long[] answer;

    static void tSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (build[i] == 0) {
                queue.add(i);
                answer[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Object n : order[current]) {
                int next = (Integer) n;
                build[next]--;
                answer[next] = Math.max(answer[current] + time[next], answer[next]);
                if (build[next] == 0) {
                    queue.add(next);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            build = new int[N + 1];
            order = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                order[i] = new ArrayList<Integer>();
            }
            time = new int[N + 1];
            answer = new long[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                order[a].add(b);
                build[b]++;
            }
            tSort();
            W = Integer.parseInt(br.readLine());
            sb.append(answer[W]).append("\n");
        }
        System.out.println(sb);
    }
}
