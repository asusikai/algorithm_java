import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parts = new int[1000001];

    static void init(){
        for(int i = 1; i<=1000000; i++){
            parts[i] = -1;
        }
    }

    static int find(int a){

        if(parts[a] < 0){
            return a;
        }

        int b = find(parts[a]);
        parts[a] = b;

        return b;
    }
    static void union(int a, int b){

        a = find(a);
        b = find(b);

        if(a != b){
            parts[a] += parts[b];
            parts[b] = a;
        }

    }

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            if (type.equals("I")){
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            }else{
                int answer = -parts[find(a)];
                sb.append(answer).append("\n");
            }
        }

        System.out.println(sb);

    }
}
