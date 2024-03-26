import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[] parent;
    static int[] distance;

    static int find(int a) {
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b, int d) {
        int p1 = find(a);
        int p2 = find(b);

        if (p1 == p2) return false;

        distance[p1] += distance[p2] + d;
        parent[p2] = p1;
        return true;
    }

    static class edge implements Comparable<edge> {
        int start;
        int end;
        int d;

        edge(int start, int end, int d) {
            this.start = start;
            this.end = end;
            this.d = d;
        }

        @Override
        public int compareTo(edge o) {
            if (this.d > o.d) {
                return 1;
            } else if (this.d < o.d) {
                return -1;
            }
            return 0;
        }
    }

    static edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        edges = new edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            edges[i] = new edge(a, b, d);
        }

        Arrays.sort(edges);
        for (int i = 0; i < E; i++) {
            union(edges[i].start, edges[i].end, edges[i].d);
        }

        int p = find(1);
        System.out.println(distance[p]);
    }
}
