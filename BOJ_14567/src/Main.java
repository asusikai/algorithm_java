import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] connection;
    static int[] degree;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        connection = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            connection[i] = new ArrayList<>();
        }
        degree = new int[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            degree[b]++;
            connection[a].add(b);
        }

        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                que.add(i);
                answer[i] = 1;
            }
        }

        while (!que.isEmpty()) {

            int current = que.poll();
            for (Integer next : connection[current]) {
                if (--degree[next] == 0) {
                    que.add(next);
                    answer[next] = answer[current] + 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
