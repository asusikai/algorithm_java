import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int s, N, K;
    static int r1, r2, c1, c2;
    static int[][] fractal;

    static void makeFractal(int s, int startY, int startX, boolean isBlack) {

        if (startY > r2 || startX > c2) return;
        int size = (int) Math.pow(N, s);
        if (startY + size <= r1 || startX + size <= c1) return;

        if (s == 0) {
            fractal[startY - r1][startX - c1] = isBlack ? 1 : 0;
            return;
        }

        int startBlack = (N - K) / 2;
        int endBlack = N - startBlack;

        for (int i = 0; i < N; i++) {
            int ny = startY + size / N * i;
            for (int j = 0; j < N; j++) {
                int nx = startX + size / N * j;
                makeFractal(s - 1, ny, nx, isBlack || ((i >= startBlack && i < endBlack) && (j >= startBlack && j < endBlack)));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        fractal = new int[r2 - r1 + 1][c2 - c1 + 1];
        makeFractal(s,0,0,false);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<=r2-r1;i++){
            for(int j = 0; j<=c2-c1; j++){
                sb.append(fractal[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
