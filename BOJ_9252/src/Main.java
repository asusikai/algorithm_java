import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String A, B;
    static int[][] LCS;
    static String answer;

    static void findLCS(int aLen, int bLen) {
        for (int i = 0; i < aLen; i++) {
            for (int j = 0; j < bLen; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    LCS[i + 1][j + 1] = LCS[i][j] + 1;
                } else {
                    LCS[i + 1][j + 1] = Math.max(LCS[i][j + 1], LCS[i + 1][j]);
                }
            }
        }
    }

    static String getLCS(int aLen, int bLen) {
        StringBuilder answer = new StringBuilder();
        int answerLen = LCS[aLen][bLen];

        while (answerLen > 0) {
            if (LCS[aLen - 1][bLen] == answerLen) {
                aLen--;
                continue;
            }

            if (LCS[aLen][bLen - 1] == answerLen) {
                bLen--;
                continue;
            }
            answer.append(A.charAt(aLen - 1));
            aLen--;
            bLen--;
            answerLen--;
        }
        return answer.reverse().toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();

        int aLen = A.length();
        int bLen = B.length();

        LCS = new int[aLen + 1][bLen + 1];

        findLCS(aLen, bLen);
        System.out.println(LCS[aLen][bLen]);
        System.out.println(getLCS(aLen,bLen));
    }
}