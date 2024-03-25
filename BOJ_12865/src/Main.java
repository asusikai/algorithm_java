import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] stuff;
    static int[][] bag;

    static void dp() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (stuff[i][0] > j) {
                    bag[j][i] = bag[j][i - 1];
                } else {
                    bag[j][i] = Math.max(bag[j][i - 1], bag[j - stuff[i][0]][i - 1] + stuff[i][1]);
                }
//                System.out.println(i + "    " + j);
//                printDp();
            }
        }
    }

    static void printDp() {
        System.out.println("===");
        for (int i = 0; i < K + 1; i++) {
            System.out.println(Arrays.toString(bag[i]));
        }
        System.out.println("===");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stuff = new int[N + 1][2];
        bag = new int[K + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stuff[i][0] = Integer.parseInt(st.nextToken());
            stuff[i][1] = Integer.parseInt(st.nextToken());
        }

        dp();
        System.out.println(bag[K][N]);
    }
}
