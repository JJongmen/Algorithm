import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] board;
    private static int max = 2;
    private static int N;

    private static void func(int cur) {
        if (cur == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, board[i][j]);
                }
            }
            return;
        }
        int[][] tmp = deepCopy(board);
        moveLeft();
        func(cur + 1);
        board = deepCopy(tmp);
        moveRight();
        func(cur + 1);
        board = deepCopy(tmp);
        moveUp();
        func(cur + 1);
        board = deepCopy(tmp);
        moveDown();
        func(cur + 1);
    }

    private static int[][] deepCopy(int[][] original) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            tmp[i] = original[i].clone();
        }
        return tmp;
    }

    private static void moveLeft() {
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

    private static void moveUp() {
        for (int j = 0; j < N; j++) {
            Deque<Integer> deque = new LinkedList<>();
            boolean combineFlag = false;
            for (int i = 0; i < N; i++) {
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
            for (int i = 0; i < N; i++) {
                if (!deque.isEmpty()) {
                    board[i][j] = deque.poll();
                    continue;
                }
                board[i][j] = 0;
            }
        }
    }

    private static void moveDown() {
        for (int j = 0; j < N; j++) {
            Deque<Integer> deque = new LinkedList<>();
            boolean combineFlag = false;
            for (int i = N - 1; i >= 0; i--) {
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
            for (int i = N - 1; i >= 0; i--) {
                if (!deque.isEmpty()) {
                    board[i][j] = deque.poll();
                    continue;
                }
                board[i][j] = 0;
            }
        }
    }

    private static void moveRight() {
        for (int i = 0; i < N; i++) {
            Deque<Integer> deque = new LinkedList<>();
            boolean combineFlag = false;
            for (int j = N - 1; j >= 0; j--) {
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
            for (int j = N - 1; j >= 0; j--) {
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
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func(0);
        System.out.println(max);
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%5d", board[i][j]);
            }
            System.out.println();
        }
    }
}