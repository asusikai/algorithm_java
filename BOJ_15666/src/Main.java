import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    static HashSet<Integer> numberSet = new HashSet<>(8);
    static Integer[] numbers;
    static int[] series;

    static void getSeries(int count, int start) {

        if (count == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(series[i]);
                System.out.print(" ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            series[count] = numbers[i];
            getSeries(count + 1, i);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        series = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numberSet.add(Integer.parseInt(st.nextToken()));
        }

        numbers = numberSet.toArray(new Integer[0]);
        Arrays.sort(numbers);
        getSeries(0, 0);
    }
}
