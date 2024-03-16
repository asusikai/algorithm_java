import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int[] dp = new int[12];

    static void getDp(int sum) {

        if (sum == 11) {
            return;
        }

        for (int i = 1; i < 4; i++) {
            if (sum + i <= 11) {
                dp[sum + i]++;
                getDp(sum + i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        getDp(0);
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
