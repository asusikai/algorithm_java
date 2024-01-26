import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {

    static int N, M;
    static Integer[] robots;
    static ArrayList<Integer> carriages;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        robots = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            robots[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(robots, Collections.reverseOrder());

        M = Integer.parseInt(br.readLine());
        carriages = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int weight = Integer.parseInt(st.nextToken());
            if (weight > robots[0]) {
                System.out.println(-1);
                return;
            }
            carriages.add(weight);
        }
        carriages.sort(Collections.reverseOrder());

        int time = 0;
        while (!carriages.isEmpty()) {
            int ci = 0;
            for (int i = 0; i < N; ) {
                if (ci == carriages.size()) break;
                if (robots[i] >= carriages.get(ci)) {
                    carriages.remove(ci);
                    i++;
                } else ci++;
            }
            time++;
        }
        System.out.println(time);
    }
}
