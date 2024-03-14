import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] paper;
    static int N;
    static int one = 0;
    static int zero = 0;

    static void check(int size, int y, int x) {
        boolean[] num = new boolean[2];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                num[paper[i][j]] = true;
                if (num[0] && num[1]) {

                    check(size / 2, y, x);
                    check(size / 2, y + size / 2, x);
                    check(size / 2, y, x + size / 2);
                    check(size / 2, y + size / 2, x + size / 2);

                    break;
                }
            }
            if (num[0] && num[1]) {
                break;
            }
        }

        if (!(num[0] && num[1])) {
            if (num[0]) {
                zero++;
            }

            if (num[1]) {
                one++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        paper = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check(N, 0, 0);
        System.out.println(zero);
        System.out.println(one);
    }
}
