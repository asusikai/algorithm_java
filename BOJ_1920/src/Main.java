import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] n = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            n[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        Integer[] m = new Integer[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int start = 0;
            int end = N - 1;
            boolean isIn = false;
            while (start < end) {
                int mid = (start + end) / 2;
                if (n[mid].equals(m[i])) {
                    isIn = true;
                    break;
                }

                if (start == mid || end == mid) break;

                if (n[mid] > m[i]) {
                    end = mid;
                    continue;
                }

                if (n[mid] < m[i]) {
                    start = mid;
                }
            }

            if (n[start].equals(m[i]) || n[end].equals(m[i])) {
                isIn = true;
            }

            if (!isIn) {
                sb.append("0").append("\n");
            } else {
                sb.append("1").append("\n");
            }
        }
        System.out.println(sb);
    }
}
