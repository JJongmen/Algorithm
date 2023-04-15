import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int INF = 10000;
    static boolean[][] original = new boolean[10][10];
    static boolean[][] board = new boolean[10][10];
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String row = br.readLine();
            for (int j = 0; j < 10; j++) {
                if (row.charAt(j) == 'O') original[i][j] = true;
            }
        }

        int result = INF;
        // 첫째 줄에서 누를 스위치의 패턴
        for (int start = 0; start < (1 << 10); start++) {
            int cnt = 0;
            // 원본 보드로 초기화
            for (int i = 0; i < 10; i++) {
                board[i] = original[i].clone();
            }
            
            // 스위치 패턴에 따라 스위치를 누름
            for (int i = 0; i < 10; i++) {
                if ((start & (1 << i)) > 0) {
                    hit(0, i);
                    cnt++;
                } 
            }

            // 2번째 줄부터 마지막 줄까지 윗 줄이 켜져 있다면 스위치를 누름
            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board[i - 1][j]) {
                        hit(i, j);
                        cnt++;
                    }
                }
            }

            if (allOff()) {
                result = Math.min(result, cnt);
            }
        }
        System.out.println(result == INF ? -1 : result);
    }

    private static boolean allOff() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j]) return false;
            }
        }
        return true;
    }

    private static void hit(int x, int y) {
        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx > 9 || ny < 0 || ny > 9) continue;
            board[nx][ny] = !board[nx][ny];
        }
    }
}