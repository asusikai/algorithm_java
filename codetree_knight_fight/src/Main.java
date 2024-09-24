import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Knight{
        int r, c, h, w, k, d, idx;
        boolean isDead;

        public Knight(int r, int c, int h, int w, int k, int idx) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.d = 0;
            this.idx = idx;
            this.isDead = false;
        }

        public int[] shield(){
            return new int[]{r,c,r+h,c+w};
        }
    }

    static int L,N,Q;
    static int[][] matrix;
    static Knight[] knights;
    static boolean move(int start, int d){
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};

        Queue<Knight> knightQueue = new LinkedList<>();
        knightQueue.add(knights[start]);
        boolean[] visited = new boolean[N+1];
        boolean[] moved = new boolean[N+1];
        visited[start] = true;
        boolean isMovable = true;

        while(!knightQueue.isEmpty() && isMovable){
            Knight current = knightQueue.poll();

            current.r += dr[d];
            current.c += dc[d];
            moved[current.idx]= true;

            if(current.r <= 0 || current.r+current.h-1 > L || current.c <= 0 || current.c+current.w-1 > L){
                isMovable = false;
                continue;
            }

            if(isWall(current)){
                isMovable = false;
                continue;
            }

            for(int i = 1; i<= N; i++){
                if(visited[i]) continue;
                if(knights[i].isDead) continue;
                if(checkShield(current, knights[i]) || checkShield(knights[i],current)){
                    knightQueue.add(knights[i]);
                    visited[i] = true;
                }
            }
        }

        if(!isMovable){
            for(int i = 1; i<=N; i++){
                if(moved[i]){
                    knights[i].r -= dr[d];
                    knights[i].c -= dc[d];
                }
            }
            return false;
        }

        for(int i = 1; i<=N; i++){
            if(moved[i] && i != start){
                checkTrap(knights[i]);
            }
        }
        return true;
    }

    static boolean checkShield(Knight k1, Knight k2){

        if((k2.r >= k1.r && k2.r <= k1.r+k1.h-1) || (k2.r+k2.h-1 >= k1.r && k2.r+k2.h <= k1.r+k1.h)){
            if(k2.c >= k1.c && k2.c <= k1.c + k1.w-1){
                return true;
            }
            if(k2.c + k2.w-1 >= k1.c && k2.c+k2.w <= k1.c + k1.w){
                return true;
            }
        }
        return false;
    }

    static boolean isWall(Knight knight){
        for(int i = knight.r; i< knight.r + knight.h; i++){
            for(int j = knight.c; j< knight.c+ knight.w; j++){
                if(matrix[i][j] == 2) return true;
            }
        }
        return false;
    }

    static void checkTrap(Knight knight){
        for(int i = knight.r; i< knight.r + knight.h; i++){
            for(int j = knight.c; j< knight.c+ knight.w; j++){
                if(matrix[i][j] == 1){
                    knight.d++;
                }
            }
        }
        if(knight.d >= knight.k){
            knight.isDead= true;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        matrix= new int[L+1][L+1];
        knights = new Knight[N+1];

        for(int i = 1; i<=L; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=L; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            knights[i] = new Knight(r,c,h,w,k,i);
        }

        for(int i =0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if(!knights[idx].isDead) {
                move(idx, dir);
            }
        }
        int answer = 0;
        for(int i = 1; i<= N; i++){
            if(!knights[i].isDead){
                answer += knights[i].d;
            }
        }

        System.out.println(answer);
    }
}
