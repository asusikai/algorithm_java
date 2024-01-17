import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Main {

    static int[][] digits = new int[36][52];
    static int N,K;
    static HashSet<Integer> existDigits = new HashSet<>();
    static BigInteger gapSum(int digit){

        BigInteger gap = BigInteger.valueOf(35-digit);
        BigInteger sum = BigInteger.valueOf(0);
        for(int i = 0; i<51; i++){
            BigInteger d = BigInteger.valueOf(digits[digit][i]);
            BigInteger p = BigInteger.valueOf(1);
            for(int j = 0; j<i; j++){
                p = p.multiply(BigInteger.valueOf(36));
            }
            sum = sum.add(d.multiply(p).multiply(gap));
        }
        return sum;
    }

    static void printChar(BigInteger num){

        StringBuilder st = new StringBuilder();

        while(!num.divide(BigInteger.valueOf(36)).equals(BigInteger.valueOf(0))){
            int mod = (num.remainder(BigInteger.valueOf(36))).intValue();
            BigInteger quotient = num.divide(BigInteger.valueOf(36));

            if (mod < 10){
                st.append((char)(mod+'0'));
            }else{
                st.append((char)(mod-10+'A'));
            }
            num = quotient;
        }

        int numInt = num.intValue();

        if (numInt >= 0 && numInt < 10){
            st.append((char)(numInt+'0'));
        }else{
            st.append((char)(numInt-10+'A'));
        }

        st.reverse();
        System.out.println(st);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        BigInteger currentSum = BigInteger.valueOf(0);
        for(int i = 0; i<N; i++){
            String st = br.readLine();
            for(int j = 0; j<st.length(); j++){
                char c = st.charAt(j);
                int num;

                if(c>='0' && c<='9'){
                    num = c - '0';
                }else{
                    num = c - 'A' + 10;
                }

                BigInteger p = BigInteger.valueOf(1);

                for(int k = 0; k<st.length()-j-1; k++){
                    p = p.multiply(BigInteger.valueOf(36));
                }

                currentSum = currentSum.add(p.multiply(BigInteger.valueOf(num)));
                existDigits.add(num);
                digits[num][st.length()-j-1]++;
            }
        }
        K = Integer.parseInt(br.readLine());

        BigInteger[] sum = new BigInteger[36];

        for(int i = 0; i<36; i++){
            sum[i] = BigInteger.valueOf(0);
        }

        for(int ed: existDigits){
            sum[ed] = gapSum(ed);
        }

        Arrays.sort(sum, Comparator.reverseOrder());

        for(int i = 0; i<K; i++){
            currentSum = currentSum.add(sum[i]);
        }
        printChar(currentSum);

    }
}
