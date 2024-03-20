import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] city;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        city = new int[N + 1][N + 1];

        int[] dis = new int[N + 1];
        Arrays.fill(dis, 1000000000);

        for (int i = 1; i <= N; i++) {
            Arrays.fill(city[i], 1000000000);
        }

        for (int i = 1; i <= N; i++) {
            city[i][i] = 0;
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            city[A][B] = Math.min(distance, city[A][B]);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dis[start] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                for (int k = 1; k <= N; k++) {
                    if (dis[k] > dis[j] + city[j][k]) {
                        dis[k] = dis[j] + city[j][k];
                    }
                }
            }
        }
        System.out.println(dis[end]);
    }
}
