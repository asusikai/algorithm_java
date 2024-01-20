import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arrows;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arrows = new int[1000002];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int balloon = Integer.parseInt(st.nextToken());
            if (arrows[balloon + 1] > 0) {
                arrows[balloon + 1]--;
            }

            arrows[balloon]++;
        }

        int shoot = 0;
        for (int i = 0; i < 1000002; i++) {
            shoot += arrows[i];
        }

        System.out.println(shoot);
    }

}
