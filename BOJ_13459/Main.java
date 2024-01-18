import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] board;
    static int N, M;

    // 상우하좌
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static int bfs(int by, int bx, int ry, int rx){

        boolean[][][][] visited = new boolean[N][M][N][M];
        Queue<int[]> positionQue = new LinkedList<>();

        visited[by][bx][ry][rx] = true;
        positionQue.offer(new int[]{by, bx, ry, rx, 0});

        while(!positionQue.isEmpty()){

            int[] current = positionQue.poll();

            for(int i = 0; i<4; i++){
                boolean blueFirst = false;

                int blueY = current[0];
                int blueX = current[1];
                int redY = current[2];
                int redX = current[3];
                int moveCnt = current[4]+1;

                if( moveCnt > 10) break;

                if(i==0 && blueY < redY) blueFirst = true;
                if(i==1 && blueX > redX) blueFirst = true;
                if(i==2 && blueY > redY) blueFirst = true;
                if(i==3 && blueX < redX) blueFirst = true;

                while(board[blueY][blueX] == '.'){
                    blueY += dy[i];
                    blueX += dx[i];
                }

                while(board[redY][redX] == '.'){
                    redY += dy[i];
                    redX += dx[i];
                }

                if(board[blueY][blueX] == 'O'){
                    continue;
                }else{
                    if(board[redY][redX] == 'O'){
                        return 1;
                    }
                }

                if(board[blueY][blueX] == '#'){
                    blueY -= dy[i];
                    blueX -= dx[i];
                }

                if(board[redY][redX] == '#'){
                    redY -= dy[i];
                    redX -= dx[i];
                }


                if(blueFirst){
                    if(blueY == redY && blueX == redX){
                        redY -= dy[i];
                        redX -= dx[i];
                    }
                }else{
                    if(blueY == redY && blueX == redX){
                        blueY -= dy[i];
                        blueX -= dx[i];
                    }
                }
                if(!visited[blueY][blueX][redY][redX]){
                    visited[blueY][blueX][redY][redX] = true;
//                    System.out.println(moveCnt);
//                    System.out.printf("%d %d %d %d\n",blueY, blueX, redY, redX);
                    positionQue.offer(new int[]{blueY, blueX, redY, redX,moveCnt});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        int by = -1, bx= -1, ry= -1, rx= -1;

        for(int i = 0; i<N; i++){
            String line = br.readLine();
            for(int j = 0; j<M; j++){
                char c = line.charAt(j);
                if (c == 'B'){
                    by = i;
                    bx = j;
                    board[i][j] = '.';
                } else if (c == 'R') {
                    ry = i;
                    rx = j;
                    board[i][j] = '.';
                }else{
                    board[i][j] = c;
                }
            }
        }

        int answer = 0;
        answer += bfs(by, bx, ry, rx);

        System.out.println(answer);
    }
}
