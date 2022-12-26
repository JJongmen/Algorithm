import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        fill(0, 0);
    }

    private static void fill(int row, int col) {
        if (col == 9) {
            fill(row + 1, 0);
            return;
        }

        if (row == 9) {
            for (int i = 0; i < 9; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(' ');
                }
                System.out.println(sb);
            }
            System.exit(0);
        }

        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (canPlace(row, col, i)) {
                    board[row][col] = i;
                    fill(row, col + 1);
                    board[row][col] = 0;
                }
            }
            return;
        }

        fill(row, col + 1);
    }

    private static boolean canPlace(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}