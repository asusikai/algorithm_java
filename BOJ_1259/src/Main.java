import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringBuilder sb = new StringBuilder();
        while (true) {
            input = br.readLine();
            if (input.equals("0")) break;

            int len = input.length();

            int start = 0;
            int end = len - 1;

            boolean isFel = true;

            while (start < end) {
                if (input.charAt(start) != input.charAt(end)) {
                    isFel = false;
                    break;
                }
                start++;
                end--;
            }

            if (isFel) {
                sb.append("yes").append("\n");
            } else {
                sb.append("no").append("\n");
            }
        }
        System.out.println(sb);
    }
}
