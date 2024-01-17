import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class island{
        int cnt;

        ArrayList<Integer> bridge;

        island(int cnt){
            this.cnt = cnt;
        }
    }
    static island[] islands;
    static boolean[] isLast;

    static long dfs(int idx){

        long sum = 0;

        for(int next: islands[idx].bridge){
            sum += dfs(next);
        }
        return Math.max(sum + islands[idx].cnt, 0);
    }

    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        islands = new island[N+1];
        for(int i = 1; i<N+1; i++){
            islands[i] = new island(0);
            islands[i].bridge = new ArrayList<Integer>();
        }
        isLast = new boolean[N+1];
        Arrays.fill(isLast, true);
        StringTokenizer st;
        for(int i = 2; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            String type = st.nextToken();
            int count = Integer.parseInt(st.nextToken());
            int bridge = Integer.parseInt(st.nextToken());

            if(type.equals("W")){
                count *= -1;
            }
            isLast[bridge] = false;
            islands[i].cnt = count;
            islands[bridge].bridge.add(i);
        }

        long answer = dfs(1);

        System.out.println(answer);
    }
}
