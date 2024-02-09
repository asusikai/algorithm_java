import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] board;

    static int paintCnt(int y, int x) {
        int startW = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    if ((j % 2 == 0 && board[y + i][x + j] == 'W') || (j % 2 == 1 && board[y + i][x + j] == 'B')) {
                        startW++;
                    }
                } else {
                    if ((j % 2 == 0 && board[y + i][x + j] == 'B') || (j % 2 == 1 && board[y + i][x + j] == 'W')) {
                        startW++;
                    }
                }
            }
        }

        return Math.min(startW, 64 - startW);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        int answer = 64;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                answer = Math.min(paintCnt(i, j), answer);
            }
        }
        System.out.println(answer);
    }
}
