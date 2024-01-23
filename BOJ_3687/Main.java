import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static String getMax(int n) {
        int q = n / 2;

        if (n % 2 == 0) {
            return "1".repeat(q);
        }

        return "7" + "1".repeat(q - 1);
    }

    static String[] oneDigit = {"", "", "1", "7", "4", "2", "6", "8"};
    static String[] twoDigits = {"", "10", "18", "22", "20", "28", "68"};
    static String[] threeDigits = {"", "108", "188", "200", "208", "288", "688"};

    static String getMin(int n) {
        int q = n / 7;
        int mod = n % 7;
        if (q == 0) {
            return oneDigit[mod];
        }
        if (mod == 0) {
            return "8".repeat(q);
        }
        if (q < 2) {
            String start = twoDigits[mod];
            return start + "8".repeat(q - 1);
        }
        String start = threeDigits[mod];
        return start + "8".repeat(q - 2);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int n;
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            bw.write(getMin(n));
            bw.write(" ");
            bw.write(getMax(n));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
