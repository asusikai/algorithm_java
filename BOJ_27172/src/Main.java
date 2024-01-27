import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        N = Integer.parseInt(br.readLine());
        int[] players = new int[N];
        int[] scores = new int[1000001];
        boolean[] isPlayer = new boolean[1000001];

        int max = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (max < num) {
                max = num;
            }
            players[i] = num;
            isPlayer[num] = true;
        }

        for (int i = 0; i < N; i++) {
            int num = players[i];
            for (int j = num * 2; j <= max; j += num) {
                if (isPlayer[j]) {
                    scores[num]++;
                    scores[j]--;
                }

            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(scores[players[i]]).append(" ");
        }
        System.out.println(sb);
    }
}
