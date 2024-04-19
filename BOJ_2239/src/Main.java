import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int[][] sudoku;
    static int[] sideCheck;
    static int[] downCheck;
    static int[][] boxCheck;
    static ArrayList<int[]> blank = new ArrayList<>();
    static int blankSize;

    static void fill(int idx) {
        if (idx == blankSize) {
            print();
            System.exit(0);
        }

        int i = blank.get(idx)[0];
        int j = blank.get(idx)[1];

        for (int num = 1; num <= 9; num++) {
            if ((sideCheck[i] & (1 << num)) == 1 << num) continue;
            if ((downCheck[j] & (1 << num)) == 1 << num) continue;
            if ((boxCheck[i / 3][j / 3] & (1 << num)) == 1 << num) continue;

            sideCheck[i] |= 1 << num;
            downCheck[j] |= 1 << num;
            boxCheck[i / 3][j / 3] |= 1 << num;
            sudoku[i][j] = num;

            fill(idx + 1);
            sideCheck[i] -= 1 << num;
            downCheck[j] -= 1 << num;
            boxCheck[i / 3][j / 3] -= 1 << num;
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]);
            }
            if(i != 8) sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];
        sideCheck = new int[9];
        downCheck = new int[9];
        boxCheck = new int[3][3];

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = input.charAt(j) - '0';
                sudoku[i][j] = num;
                sideCheck[i] |= 1 << num;
                downCheck[j] |= 1 << num;
                boxCheck[i / 3][j / 3] |= 1 << num;

                if (num == 0) {
                    blank.add(new int[]{i, j});
                }
            }
        }
        blankSize = blank.size();
        fill(0);
    }
}
