import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    private static char[][] board;

    private static void printStar(int n, int x, int y) {
        if (n == 3) {
            for (int dy = 0; dy < 3; dy++) {
                board[x][y + dy] = '*';
                board[x + 2][y + dy] = '*';
            }
            board[x + 1][y] = '*';
            board[x + 1][y + 2] = '*';
            return;
        }
        int third = n / 3;
        for (int dy = 0; dy <= third * 2; dy += third) {
            printStar(third, x, y + dy);
            printStar(third, x + third * 2, y + dy);
        }
        printStar(third, x + third, y);
        printStar(third, x + third, y + third * 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        board = new char[N][N];
        printStar(N, 0, 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(board[i][j] == '*' ? '*' : ' ');
            }
            bw.write('\n');
        }
        bw.flush();
        bw.close();
    }
}