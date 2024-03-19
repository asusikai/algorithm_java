import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] box;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int answer = 0;

    static void BFS(int[][] startPoints, int startCnt) {

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < startCnt; i++) {
            int y = startPoints[i][0];
            int x = startPoints[i][1];

            visited[y][x] = true;
            queue.add(new int[]{y, x});
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 | nx >= M) continue;
                if (visited[ny][nx]) continue;
                if (box[ny][nx] == -1) continue;

                queue.add(new int[]{ny, nx});
                visited[ny][nx] = true;
                box[ny][nx] = box[y][x] + 1;
            }
            if (answer < box[y][x]) {
                answer = box[y][x];
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        int[][] start = new int[N * M][2];
        int startCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                box[i][j] = num;

                if (num == 1) {
                    start[startCnt][0] = i;
                    start[startCnt][1] = j;
                    startCnt++;
                }
            }
        }
        BFS(start, startCnt);

        boolean notDone = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    notDone = true;
                    break;
                }
            }
        }

        if (notDone) {
            System.out.println(-1);
        } else {
            System.out.println(answer - 1);
        }
    }
}
