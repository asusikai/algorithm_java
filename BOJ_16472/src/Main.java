import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] alphabets = new int[26];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        int max = 0;
        int alpCnt = 0;
        int start = 0;
        int end = 1;

        alphabets[input.charAt(start) - 'a']++;
        alpCnt++;

        while (end < input.length()) {

            if (alphabets[input.charAt(end) - 'a'] == 0) {
                alpCnt++;
            }
            alphabets[input.charAt(end) - 'a']++;

            if (alpCnt > N) {
                if (max < end - start) {
                    max = end - start;
                }

                while (true) {
                    alphabets[input.charAt(start) - 'a']--;
                    if (alphabets[input.charAt(start) - 'a'] == 0) {
                        start++;
                        alpCnt--;
                        break;
                    }
                    start++;
                }
            }
            end++;
        }
        int last = 0;
        for(int i = 0; i<26; i++){
            last += alphabets[i];
        }
        System.out.println(Math.max(last, max));
    }
}
