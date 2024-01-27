import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static int[][] matrix;
    static int N, M;
    static int time = 0;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static void findOutside(int y, int x) {
        if (matrix[y][x] != 0) return;
        matrix[y][x] = -1;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

            if (matrix[ny][nx] == 0) {
                findOutside(ny, nx);
            }
        }
    }

    static ArrayList<int[]> nextMelt = new ArrayList<>();

    static void meltCheese(int y, int x) {

        int air = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (matrix[ny][nx] == -1) air++;
        }

        if (air >= 2) {
            nextMelt.add(new int[]{y, x});
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        findOutside(0, 0);

        boolean allMelt = false;
        while (!allMelt) {
            time++;
            nextMelt.clear();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (matrix[i][j] == 1) {
                        meltCheese(i, j);
                    }
                }
            }

            for (int[] arr : nextMelt) {
                matrix[arr[0]][arr[1]] = 0;
            }

            for (int[] arr : nextMelt) {
                findOutside(arr[0], arr[1]);
            }
            if (nextMelt.isEmpty()) {
                time--;
                allMelt = true;
            }
        }
        System.out.println(time);

    }
}
