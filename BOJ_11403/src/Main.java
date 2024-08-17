import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] dfs(int[][] matrix, int N, int start) {

        int[] visited = new int[N];
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        while(!stack.isEmpty()){
            int current = stack.pop();
            for(int i = 0; i<N; i++){
                if(visited[i] == 1 || matrix[current][i] == 0) continue;
                stack.add(i);
                visited[i] = 1;
            }
        }
        return visited;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] connection = new int[N][];
        for(int i = 0; i < N; i++){
            connection[i] = dfs(matrix, N, i);
        }

        for(int i = 0; i< N;i++){
            for(int j =0; j<N; j++){
                System.out.print(connection[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
}
