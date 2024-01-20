import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static void dfs(int y, int x, boolean[][] visited) {

        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N | nx < 0 || nx >= M) continue;
            if (visited[ny][nx]) continue;
            if (map[ny][nx] <= 0) continue;
            dfs(ny, nx, visited);
        }
    }

    static int countIce() {

        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void bfs() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> positionQ = new LinkedList<>();

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(map[i][j]>0){
                    positionQ.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!positionQ.isEmpty()) {
            int[] current = positionQ.poll();

            for (int i = 0; i < 4; i++) {
                int ny = current[0] + dy[i];
                int nx = current[1] + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

                if(map[ny][nx] <= 0 && !visited[ny][nx]){
                    map[current[0]][current[1]]--;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;

        while (true) {
            int iceCnt = countIce();
            if (iceCnt == 0) {
                System.out.println(0);
                return;
            }
            if (iceCnt >= 2) {
                System.out.println(year);
                return;
            }
            bfs();
            year++;
        }
    }
}