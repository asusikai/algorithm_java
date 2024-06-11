import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] array;
    static boolean[] visited;
    static boolean[] done;
    static int answer;

    static void select(int idx) {
        if (done[idx]) return;

        if (visited[idx]) {
            done[idx] = true;
            answer++;
        }
        visited[idx] = true;
        select(array[idx]);
        done[idx] = true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            array = new int[N + 1];
            visited = new boolean[N + 1];
            done = new boolean[N + 1];
            answer = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= N; i++) {
                if (done[i]) continue;
                select(i);
            }

            System.out.println(N - answer);
        }
    }
}
