import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] board;

    static class ball {
        int y;
        int x;

        ball(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static ball move(ball ball, ball other, int dir) {
        int ny = ball.y;
        int nx = ball.x;

        while (true) {
            int tempy = ny + dy[dir];
            int tempx = nx + dx[dir];
            if (board[tempy][tempx] == 'O') return new ball(-1, -1);
            if (board[tempy][tempx] == '#') break;
            if (tempy == other.y && tempx == other.x) break;
            ny += dy[dir];
            nx += dx[dir];
        }
        return new ball(ny, nx);
    }

    static int bfs(ball blue, ball red) {
        boolean[][][][] visited = new boolean[N][M][N][M];
        Queue<ball[]> queue = new LinkedList<>();

        queue.add(new ball[]{blue, red, new ball(0, 0)});
        visited[blue.y][blue.x][red.y][red.x] = true;

        while (!queue.isEmpty()) {
            ball[] current = queue.poll();
            if (current[2].y == 10) continue;
            for (int i = 0; i < 4; i++) {
                ball nextBlue, nextRed;
                if (current[0].y * dy[i] + current[0].x * dx[i] > current[1].y * dy[i] + current[1].x * dx[i]) {
                    nextBlue = move(current[0], current[1], i);
                    nextRed = move(current[1], nextBlue, i);

                } else {
                    nextRed = move(current[1], current[0], i);
                    nextBlue = move(current[0], nextRed, i);
                }

                if (nextBlue.y == -1 && nextBlue.x == -1) continue;
                if (nextRed.y == -1 && nextRed.x == -1) return current[2].y + 1;
                if (visited[nextBlue.y][nextBlue.x][nextRed.y][nextRed.x]) continue;

                queue.add(new ball[]{nextBlue, nextRed, new ball(current[2].y + 1, 0)});
                visited[nextBlue.y][nextBlue.x][nextRed.y][nextRed.x] = true;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        ball blue = new ball(-1, -1);
        ball red = new ball(-1, -1);
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'B') {
                    blue = new ball(i, j);
                    board[i][j] = '.';
                    continue;
                }
                if (s.charAt(j) == 'R') {
                    red = new ball(i, j);
                    board[i][j] = '.';
                    continue;
                }
                board[i][j] = s.charAt(j);
            }
        }
        System.out.println(bfs(blue, red));
    }
}
