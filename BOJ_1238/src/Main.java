import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X;

    static class node implements Comparable<node> {
        int next;
        int d;

        node(int next, int d) {
            this.next = next;
            this.d = d;
        }

        @Override
        public int compareTo(node o) {
            if (this.d < o.d) {
                return -1;
            } else if (this.d > o.d) {
                return 1;
            }
            return 0;
        }
    }

    static ArrayList<node>[] road;

    static int dijkstra(int start, int end) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, 1000001);
        distance[start] = 0;

        PriorityQueue<node> pq = new PriorityQueue<>();
        pq.add(new node(start, 0));
        while (!pq.isEmpty()) {
            node current = pq.poll();
            if (distance[current.next] < current.d) continue;
            for (node n : road[current.next]) {
                if (distance[n.next] > current.d + n.d) {
                    distance[n.next] = current.d + n.d;
                    pq.add(new node(n.next, current.d + n.d));
                }
            }
        }
        return distance[end];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        road = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            road[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            road[start].add(new node(end, d));
        }
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int d = dijkstra(i, X) + dijkstra(X, i);
            if (answer < d) {
                answer = d;
            }
        }
        System.out.println(answer);
    }
}
