import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] original;
    private static int[][] board;
    private static int N;

    private static void rotate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            tmp[i] = board[i].clone();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tmp[j][N - 1 - i];
            }
        }
    }

    private static void move(int direction) {
        while (direction-- > 0) rotate();
        for (int i = 0; i < N; i++) {
            Deque<Integer> deque = new LinkedList<>();
            boolean combineFlag = false;
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) continue;
                if (!deque.isEmpty() && deque.peekLast() == board[i][j] && !combineFlag) {
                    deque.pollLast();
                    deque.offer(board[i][j] * 2);
                    combineFlag = true;
                    continue;
                }
                deque.offer(board[i][j]);
                combineFlag = false;
            }
            for (int j = 0; j < N; j++) {
                if (!deque.isEmpty()) {
                    board[i][j] = deque.poll();
                    continue;
                }
                board[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        original = new int[N][N];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 2;
        for (int tmp = 0; tmp < 1024; tmp++) {
            for (int i = 0; i < N; i++) {
                board[i] = original[i].clone();
            }
            int dir = tmp;
            for (int i = 0; i < 5; i++) {
                move(dir % 4);
                dir /= 4;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, board[i][j]);
                }
            }
        }
        System.out.println(max);
    }
}