import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;

public class Main {

    static int N;

    static HashSet<Integer> findSubset(int num) {
        HashSet<Integer> subset = new HashSet<>();
        if (num < 10) return subset;

        String numStr = Integer.toString(num);
        int numLen = numStr.length();

        for (int i = 0; i < numLen; i++) {
            if (numStr.charAt(i) == '0') continue;

            String subString = "";
            int endIdx = i;
            while (endIdx < numLen) {
                subString = numStr.substring(i, endIdx + 1);
                endIdx++;
                subset.add(Integer.parseInt(subString));
            }
        }
        subset.remove(num);
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

        HashSet<Integer> subset = findSubset(num);
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
