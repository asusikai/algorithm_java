import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String scroll;
    static String[] bridge = new String[2];

    static int[][][] log = new int[2][100][20];

    static int dp(int scrollIdx, int isHell, int bridgeIdx) {
        if (scrollIdx == scroll.length()) {
            return 1;
        }

        if (log[isHell][bridgeIdx][scrollIdx] != -1) {
            return log[isHell][bridgeIdx][scrollIdx];
        }

        log[isHell][bridgeIdx][scrollIdx] = 0;
        for (int i = bridgeIdx; i < bridge[isHell].length(); i++) {
            if (bridge[isHell].charAt(i) == scroll.charAt(scrollIdx)) {
                log[isHell][bridgeIdx][scrollIdx] += dp(scrollIdx + 1, isHell ^ 1, i + 1);
            }
        }

        return log[isHell][bridgeIdx][scrollIdx];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        scroll = br.readLine();
        bridge[0] = br.readLine();
        bridge[1] = br.readLine();

        for (int i = 0; i < 100; i++) {
            Arrays.fill(log[0][i], -1);
            Arrays.fill(log[1][i], -1);
        }
        int answer = 0;
        answer += dp(0, 0, 0);
        answer += dp(0, 1, 0);

        System.out.println(answer);
    }
}
