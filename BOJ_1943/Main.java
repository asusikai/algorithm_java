import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] cost;

    static void find(int sum, int[][] coins) {
        for (int[] coin : coins) {
            for (int i = sum; i >= 0; i--) {
                if (i - coin[0] >= 0) {
                    if (cost[i - coin[0]] == 1) {
                        for (int j = 0; j < coin[1]; j++) {
                            if (i + coin[0] * j <= sum / 2) {
                                cost[i + coin[0] * j] = 1;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));

        for (int i = 0; i < 3; i++) {
            N = Integer.parseInt(br.readLine());
            int[][] coins = new int[N][2];

            int sum = 0;

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                coins[j][0] = value;
                coins[j][1] = cnt;
                sum += value * cnt;
            }

            if (sum % 2 == 1) {
                bw.write("0\n");
                continue;
            }
            cost = new int[sum + 1];
            cost[0] = 1;
            find(sum, coins);
            bw.write(cost[sum / 2] + "\n");
        }
        bw.flush();
        bw.close();

    }
}
