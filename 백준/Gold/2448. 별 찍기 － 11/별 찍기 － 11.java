import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    private static char[][] board;

    private static void printTriangle(int n, int x, int y) {
        if (n == 3) {
            board[x][y] = '*';
            board[x + 1][y] = '*';
            board[x + 1][y + 2] = '*';
            for (int i = 0; i < 5; i++) {
                board[x + 2][y + i] = '*';
            }
            return;
        }
        printTriangle(n / 2, x, y);
        printTriangle(n / 2, x + n / 2, y);
        printTriangle(n / 2, x + n / 2, y + n);
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = scan.nextInt();
        board = new char[N][2 * N - 1];
        printTriangle(N, 0, 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                bw.write(' ');
            }
            for (int j = 0; j < 1 + 2 * i; j++) {
                bw.write(board[i][j] == '*' ? '*' : ' ');
            }
            for (int j = 0; j < N - i - 1; j++) {
                bw.write(' ');
            }
            bw.write('\n');
        }
        bw.flush();
        bw.close();
    }
}