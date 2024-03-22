import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R;
    static int[][] country;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static boolean BFS() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        boolean moved = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                ArrayList<int[]> union = new ArrayList<>();
                queue.add(new int[]{i, j});
                visited[i][j] = true;
                int sum = country[i][j];
                int cnt = 1;
                union.add(new int[]{i,j});

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int ny = current[0] + dy[k];
                        int nx = current[1] + dx[k];

                        if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                        if (visited[ny][nx]) continue;
                        int gap = Math.abs(country[current[0]][current[1]] - country[ny][nx]);
                        if (gap < L || gap > R) continue;

                        sum += country[ny][nx];
                        cnt++;
                        visited[ny][nx] = true;
                        union.add(new int[]{ny, nx});
                        queue.add(new int[]{ny, nx});
                        moved = true;
                    }

                }
                int avg = sum / cnt;
                for (int[] yx: union) {
                    country[yx[0]][yx[1]] = avg;
                }
            }
        }
        return moved;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        country = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        while (BFS()) {
            cnt++;
        }

        System.out.println(cnt);
    }
}
