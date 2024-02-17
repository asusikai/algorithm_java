import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static Integer[] nums;
    static int N;

    static int avg() {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }

        return (int) Math.round(sum / N);
    }

    static int freq() {
        int[] minus = new int[4001];
        int[] plus = new int[4001];
        for (int i = 0; i < N; i++) {
            if (nums[i] < 0) {
                minus[-nums[i]]++;
            } else {
                plus[nums[i]]++;
            }
        }
        int max = 0;
        for (int i = 0; i < 4001; i++) {
            if (plus[i] > max) {
                max = plus[i];
            }
            if (minus[i] > max) {
                max = minus[i];
            }
        }
        ArrayList<Integer> maxNums = new ArrayList<>();
        for (int i = 4000; i > 0; i--) {
            if (maxNums.size() == 2) break;
            if (minus[i] == max) {
                maxNums.add(-i);
            }
        }
        for (int i = 0; i < 4001; i++) {
            if (maxNums.size() == 2) break;
            if (plus[i] == max) {
                maxNums.add(i);
            }
        }
        return maxNums.get(maxNums.size() - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new Integer[N];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            nums[i] = n;
        }

        Arrays.sort(nums);

        System.out.println(avg());
        System.out.println(nums[N / 2]);
        System.out.println(freq());
        System.out.println(nums[N - 1] - nums[0]);
    }
}
