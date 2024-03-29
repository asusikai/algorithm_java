import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int answer = 0;
    static int[] board;
    static int[] board2;

    static boolean isOk(int y1, int x1) {

        for (int x2 = 1; x2 < x1; x2++) {
            int y2 = board[x2];
            if (Math.abs(y1 - y2) == Math.abs(x1 - x2)) {
                return false;
            }
        }
        return true;
    }

    static void Queen(int cnt) {
        if (cnt == N + 1) {
            answer++;
            return;
        }

        for (int i = 1; i <= N; i++) {

            if (board2[i] != 0) continue;

            if (isOk(i, cnt)) {
                board[cnt] = i;
                board2[i] = cnt;
                Queen(cnt + 1);
                board[cnt] = 0;
                board2[i] = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1];
        board2 = new int[N + 1];

        Queen(1);
        System.out.println(answer);
    }
}
