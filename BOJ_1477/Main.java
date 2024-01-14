import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int L;
    static int[] rest;
    static int[] gap;

    static int longest;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        longest = L;

        st = new StringTokenizer(br.readLine()," ");

        rest = new int[N+2];
        rest[0] = 0;
        rest[N+1] = L;

        gap = new int[N+1];

        for(int i = 1; i<=N; i++){
            rest[i] = (Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(rest);

        for(int i = 1; i<N+2; i++){
            gap[i-1] = rest[i]-rest[i-1];
        }

        int[] buildCount = new int[N+1];
        Arrays.fill(buildCount, 1);

        for(int i = 0; i<M; i++){
            int maxGapIndex = -1;
            int maxGap = 0;

            for(int j = 0; j<N+1; j++){
                int current = (int) Math.ceil((double)gap[j]/buildCount[j]);
                if(maxGap < current) {
                    maxGap = current;
                    maxGapIndex = j;
                }
            }
            buildCount[maxGapIndex]++;
        }
        int answer = 0;
        for(int j = 0; j<N+1; j++){

            int current = (int) Math.ceil((double)gap[j]/buildCount[j]);
            if(answer < current) {
                answer = current;
            }
        }

        System.out.println(answer);

    }
}
