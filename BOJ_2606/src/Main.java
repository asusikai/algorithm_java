import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList[] matrix;

    static int bfs() {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        visited[1] = true;
        int virus = 0;

        while (!que.isEmpty()) {
            int current = que.poll();

            int len = matrix[current].size();

            for (int i = 0; i < len; i++) {
                if (!visited[(int) matrix[current].get(i)]) {
                    que.add((int) matrix[current].get(i));
                    visited[(int) matrix[current].get(i)] = true;
                    virus++;
                }
            }
        }
        return virus;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        matrix = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            matrix[i] = new ArrayList<Integer>();
        }


        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a].add(b);
            matrix[b].add(a);
        }

        System.out.println(bfs());
    }
}
