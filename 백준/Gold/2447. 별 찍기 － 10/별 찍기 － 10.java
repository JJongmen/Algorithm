import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    private static char[][] board;

    private static void printStar(int n, int x, int y) {
        if (n == 1) {
            board[x][y] = '*';
            return;
        }
        int third = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;
                printStar(third, x + third * i, y + third * j);
            }
        }
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