import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K;
    static int[] minDistance = new int[100001];

    static int[] minCount = new int[100001];

    static void bfs(){

        int min = 100001;
        int start = N;

        Queue<int[]> pos = new LinkedList<>();

        pos.add(new int[]{start, 0});

        while(!pos.isEmpty()){
            int[] current = pos.poll();

            if(current[1] < minDistance[current[0]]){
                minDistance[current[0]] = current[1];
                minCount[current[0]] = 1;
            }else if(current[1] == minDistance[current[0]]){
                minCount[current[0]]++;
            }else{
                continue;
            }

            if(current[0] == K){
                continue;
            }

            if(current[0]-1 >= 0) pos.add(new int[]{current[0]-1, current[1]+1});
            if(current[0]+1 <= 100000) pos.add(new int[]{current[0]+1, current[1]+1});
            if(current[0]*2 <= 100000) pos.add(new int[]{current[0]*2, current[1]+1});
        }

    }

    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(minDistance, 100001);
        bfs();

        if(N < K){
            System.out.println(minDistance[K]);
            System.out.println(minCount[K]);
        }else{
            System.out.println(N-K);
            System.out.println(1);
        }


    }
}
