import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] liquid = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            liquid[i] = Long.parseLong(st.nextToken());
        }

        int idx = 0;
        long answer = Long.MAX_VALUE;

        int a = 0, b = N - 1;
        while (idx < N - 1) {

            int start = idx + 1;
            int end = N - 1;

            while (start <= end) {
                int mid = (start + end) / 2;

                long abs = Math.abs(liquid[idx] + liquid[mid]);
                if (answer > abs) {
                    answer = abs;
                    a = idx;
                    b = mid;
                }

                if (liquid[mid] < -liquid[idx]) {
                    start = mid + 1;
                } else if (liquid[mid] > -liquid[idx]) {
                    end = mid - 1;
                } else {
                    break;
                }
            }


            idx++;
        }

        System.out.println(liquid[a] + " " + liquid[b]);
    }
}
