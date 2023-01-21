import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] rowCheck = new boolean[9][10];
    static boolean[][] colCheck = new boolean[9][10];
    static boolean[][] blockCheck = new boolean[9][10];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            String row = scan.next();
            for (int j = 0; j < 9; j++) {
                board[i][j] = row.charAt(j) - '0';
                if (board[i][j] == 0) continue;
                rowCheck[i][board[i][j]] = true;
                colCheck[j][board[i][j]] = true;
                blockCheck[getBlock(i, j)][board[i][j]] = true;
            }
        }

        fillNumber(0, 0);
    }

    private static void fillNumber(int row, int col) {
        if (row == 9) {
            printBoard();
            System.exit(0);
        }
        if (col == 9) {
            fillNumber(row + 1, 0);
            return;
        }
        if (board[row][col] != 0) {
            fillNumber(row, col + 1);
            return;
        }
        for (int num = 1; num <= 9; num++) {
            if (canPlace(row, col, num)) {
                rowCheck[row][num] = true;
                colCheck[col][num] = true;
                blockCheck[getBlock(row, col)][num] = true;
                board[row][col] = num;
                fillNumber(row, col + 1);
                rowCheck[row][num] = false;
                colCheck[col][num] = false;
                blockCheck[getBlock(row, col)][num] = false;
                board[row][col] = 0;
            }
        }
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean canPlace(int row, int col, int num) {
        return !rowCheck[row][num] && !colCheck[col][num] && !blockCheck[getBlock(row, col)][num];
    }

    private static int getBlock(int row, int col) {
        return 3 * (row / 3) + (col / 3);
    }
}