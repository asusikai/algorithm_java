import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static int answer;

    static int[][] rotate(int[][] board, int cnt) {
        if (cnt == 0) return board;
        int[][] rotatedBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotatedBoard[j][N - 1 - i] = board[i][j];
            }
        }
        return rotate(rotatedBoard, cnt - 1);
    }

    static int getMax(int[][] board, int size) {
        int val = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] > val) {
                    val = board[i][j];
                }
            }
        }
        return val;
    }

    static void move(int size, int[][] board, int cnt) {
        if (cnt == 5) {
            int val = getMax(board, size);
            answer = Math.max(val, answer);
            return;
        }
        int[][] rotated;
        for (int i = 0; i < 4; i++) {
            rotated = rotate(board, i);
            int[][] newBoard = new int[N][N];

            for (int r = 0; r < N; r++) {
                System.arraycopy(rotated[r], 0, newBoard[r], 0, N);
            }

            boolean[][] done = new boolean[N][N];

            for (int r = 1; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (newBoard[r][c] == 0) continue;
                    int nr = r;
                    while (nr > 0 && newBoard[nr - 1][c] == 0) nr--;
                    if (nr > 0 && newBoard[nr - 1][c] == newBoard[r][c] && !done[nr - 1][c]) {
                        done[nr - 1][c] = true;
                        newBoard[nr - 1][c] *= 2;
                        newBoard[r][c] = 0;
                    } else {
                        if (nr != r) {
                            newBoard[nr][c] = newBoard[r][c];
                            newBoard[r][c] = 0;
                        }
                    }
                }
            }
            move(size, newBoard, cnt + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
        move(N, board, 0);
        System.out.println(answer);
    }
}
