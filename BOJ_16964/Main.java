import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    static ArrayList[] matrix;
    static int[] order;
    static int idx;
    static int N;
    static boolean isCorrect = true;
    static boolean[] visited;

    static void checkDfs(int x) {

        if (visited[x]) return;
        visited[x] = true;

        HashSet<Integer> next = new HashSet<>();

        for (Object i : matrix[x]) {
            if (!visited[(Integer) i]) {
                next.add((Integer) i);
            }
        }

        if (next.isEmpty()) return;

        if (next.contains(order[idx])) {
            checkDfs(order[idx++]);
        } else {
            isCorrect = false;
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        matrix = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            matrix[i] = new ArrayList<Integer>();
        }
        visited = new boolean[N + 1];
        order = new int[N];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            matrix[x].add(y);
            matrix[y].add(x);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
        idx = 1;

        checkDfs(1);

        if (isCorrect) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
