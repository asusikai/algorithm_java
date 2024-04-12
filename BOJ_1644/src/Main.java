import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static boolean[] notPrimes;

    static void getPrimes(int N) {
        if (N == 1) return;
        int last = (int) Math.ceil(Math.sqrt(N));
        for (int i = 2; i <= last; i++) {
            for (int j = 2; j <= N / i; j++) {
                notPrimes[j * i] = true;
            }
        }
    }

    static int getSum(int N) {
        int start = 2;
        int end = 2;
        int sum = 2;
        int answer = 0;
        while (end <= N) {
            if (sum < N) {
                while (notPrimes[++end]) {
                    if (end > N) break;
                }
                if (!notPrimes[end]) {
                    sum += end;
                }
            }

            if (sum > N) {
                sum -= start;
                while (notPrimes[++start]) {
                    if (start == end) break;
                }
            }

            if (sum == N) {
                answer++;
                while (notPrimes[++end]) {
                    if (end > N) break;
                }
                if (!notPrimes[end] && end <= N) {
                    sum += end;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        notPrimes = new boolean[N + 2];
        getPrimes(N);

        System.out.println(getSum(N));
    }
}
