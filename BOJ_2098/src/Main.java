import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_VALUE = 16000001;
    static int N;
    static int[][] map;
    static int[][] path;
    static int TSP(int curr, int visited){
        if(visited == (1<<N) - 1){
            return (map[curr][0] != 0) ? map[curr][0]:MAX_VALUE;
        }
        if(path[visited][curr] != -1) {
            return path[visited][curr];
        }
        path[visited][curr] = MAX_VALUE;
        for(int i = 0; i<N; i++){
            if(visited != (visited | 1 << i) && map[curr][i] > 0) {
                path[visited][curr] = Math.min(path[visited][curr], map[curr][i] + TSP(i, visited | 1 << i));
            }
        }
        return path[visited][curr];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        path = new int[1<<N][N];
        for(int i = 0; i<(1 << N); i++){
            Arrays.fill(path[i], -1);
        }

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(TSP(0,1));
    }
}
