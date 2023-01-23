import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, K;
    static int[][] board = new int[201][201];
    static List<Position>[] viruses = new List[1001];
    static int totalVirusCnt = 0;


    static {
        for (int i = 1; i <= 1000; i++) {
            viruses[i] = new ArrayList<>();
        }
    }

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) continue;
                viruses[board[i][j]].add(new Position(i, j));
                totalVirusCnt++;
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int totalPlaceCnt = N * N;
        while (S-- > 0) {
            for (int virusNum = 1; virusNum <= K; virusNum++) {
                spreadVirus(virusNum);
            }
            if (totalVirusCnt == totalPlaceCnt) break;
        }
        System.out.println(board[X][Y]);
    }

    private static void spreadVirus(int virusNum) {
        int size = viruses[virusNum].size();
        for (int i = 0; i < size; i++) {
            Position virus = viruses[virusNum].get(i);
            for (int j = 0; j < 4; j++) {
                int nx = virus.x + dx[j];
                int ny = virus.y + dy[j];
                if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                if (board[nx][ny] != 0) continue;
                viruses[virusNum].add(new Position(nx, ny));
                board[nx][ny] = virusNum;
                totalVirusCnt++;
            }
        }
    }
}