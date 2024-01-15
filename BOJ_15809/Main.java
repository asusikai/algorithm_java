import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] country;
    static Integer[] army;

    static int find(int a){
        if(country[a] == a){
            return a;
        }

        return country[a] = find(country[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b){
            return;
        }

        country[b] = a;
        army[a] += army[b];
        army[b] = 0;
    }

    static void war(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b){
            return;
        }

        if(army[a]>army[b]){
            country[b] = a;
            army[a] -= army[b];
            army[b] = 0;
        }else if(army[a]<army[b]){
            country[a] = b;
            army[b] -= army[a];
            army[a] = 0;
        }else {
            army[a] = army[b] = 0;
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        country = new int[N+1];

        for(int i = 1; i<=N;i++){
            country[i] = i;
        }
        army = new Integer[N+1];
        army[0] = -1;
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            army[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int relation = Integer.parseInt(st.nextToken());
            int countryA = Integer.parseInt(st.nextToken());
            int countryB = Integer.parseInt(st.nextToken());

            if(relation == 1){
                union(countryA, countryB);
            } else if (relation == 2) {
                war(countryA, countryB);
            }
        }

        Arrays.sort(army, Collections.reverseOrder());

        int countryCnt = 0;

        for(int i = 0; i<N; i++){
            countryCnt++;
            if(army[i] == 0){
                countryCnt--;
                break;
            }
        }
        System.out.println(countryCnt);
        for(int i = countryCnt-1; i>=0; i--){
            System.out.print(army[i]);
            System.out.print(" ");
        }
    }
}
