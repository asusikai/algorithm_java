import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Integer[][] balls = new Integer[N][4];
        Integer[] colorCnt = new Integer[200001];
        Integer[] sizeCnt = new Integer[2001];

        for (int i = 0; i < N; i++) {
            balls[i][0] = 0;
            balls[i][1] = 0;
            balls[i][2] = 0;
            balls[i][3] = 0;
        }
        Arrays.fill(colorCnt, 0);
        Arrays.fill(sizeCnt, 0);

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            balls[i][0] = i;
            balls[i][1] = Integer.parseInt(st.nextToken());
            balls[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(balls, (o1, o2) -> !Objects.equals(o1[2], o2[2]) ? o1[2] - o2[2] : o1[1] - o2[1]);

        int winSum = 0;
        for (int i = 0; i < N; i++) {
            winSum += balls[i][2];
            colorCnt[balls[i][1]] += balls[i][2];
            sizeCnt[balls[i][2]] += balls[i][2];
            balls[i][3] = winSum - colorCnt[balls[i][1]] - sizeCnt[balls[i][2]] + balls[i][2];
            if (i > 0 && balls[i][1].equals(balls[i - 1][1]) && balls[i][2].equals(balls[i - 1][2])) {
                balls[i][3] = balls[i - 1][3];
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(balls[i][1] + " " + balls[i][2]);
//        }

        Arrays.sort(balls, (o1, o2) -> o1[0] - o2[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(balls[i][3]).append("\n");
        }
        System.out.println(sb);
    }
}