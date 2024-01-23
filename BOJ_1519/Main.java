import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N;

    static ArrayList<Integer> findSubset(int num) {
        ArrayList<Integer> subset = new ArrayList<>();
        String numStr = Integer.toString(num);
        int numLen = numStr.length();

        for (int i = 0; i < numLen; i++) {
            for (int j = i; j < numLen; j++) {
                int temp = num;
                for (int k = 0; k < i; k++) {
                    temp /= 10;
                }

                int n = 0;
                int pow = 1;
                for (int k = i; k <= j; k++) {
                    n += (temp % 10) * pow;
                    temp /= 10;
                    pow *= 10;
                }
                if (n == 0 || n == num) {
                    continue;
                }
                subset.add(n);
            }
        }
        return subset;
    }

    static int[] minStart;

    static int dp(int num) {
        if (minStart[num] != 0) {
            return minStart[num];
        }

        if (num < 10) {
            return minStart[num] = -1;
        }

        ArrayList<Integer> subset = findSubset(num);
        String strN = num + "";
        int thisValue = 1000001;
        boolean victory = false;

        for (Integer i : subset) {
            if (i == 0)
                continue;
            if (dp(num - i) == -1) {
                victory = true;
                thisValue = Math.min(thisValue, i);
            }

        }
        if (!victory)
            return minStart[num] = -1;

        return minStart[num] = thisValue;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minStart = new int[N + 1];

        System.out.println(dp(N));
    }
}
