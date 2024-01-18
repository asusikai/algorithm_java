import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] board = new int[101];
    static int minCount = 0;
     static void bfs(){

        boolean[] visited = new boolean[101];
        Queue<int[]> positionQ = new LinkedList<>();
        positionQ.add(new int[]{1, 0});
        visited[1] = true;

        while(!positionQ.isEmpty()){
            int[] current = positionQ.poll();
            int pos = board[current[0]];
            int cnt = current[1];

            if(pos == 100){
                minCount = cnt;
            }

            for(int i = 1; i<=6; i++){
                if((pos+i) <= 100 && !visited[pos+i]){
                    visited[pos+i] = true;
                    positionQ.add(new int[]{pos+i, cnt+1});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 1; i<=100; i++){
            board[i] = i;
        }

        for(int i = 0; i<N+M; i++){
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        bfs();

        System.out.println(minCount);
    }

}
