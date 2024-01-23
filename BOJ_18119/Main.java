import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int alphabets = 0B11111111111111111111111111;
    static int[] words;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        words = new int[N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int word_len = word.length();
            int wordNum = 0;
            for (int j = 0; j < word_len; j++) {
                char x = word.charAt(j);
                int charNum = x - 'a';
                wordNum |= 1 << charNum;
            }
            words[i] = wordNum;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            char alp = st.nextToken().charAt(0);
            int aNum = 1 << (alp - 'a');

            if (type == 1) {
                alphabets ^= aNum;
            } else {
                alphabets |= aNum;
            }
            int mem = 0;
            for (int j = 0; j < N; j++) {
                if (((alphabets & words[j])) == words[j]) {
                    mem++;
                }
            }
            bw.write(mem + "\n");
        }
        bw.flush();
        bw.close();
    }
}
