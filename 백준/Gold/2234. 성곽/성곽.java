import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board = new int[50][50]; // M * N 크기의 성
    static int[][] room = new int[50][50];  // room[i][j] = (i,j)의 방 번호
    static int[] roomArea = new int[2501];
    static int[] dx = {0, -1, 0, 1};    // 서, 북, 동, 남
    static int[] dy = {-1, 0, 1, 0};    // 서, 북, 동, 남
    static int roomCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxArea = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (room[i][j] > 0) continue;
                roomCnt++;
                roomArea[roomCnt] = dfs(i, j);
                maxArea = Math.max(maxArea, roomArea[roomCnt]);
            }
        }

        int maxCombineArea = maxArea;
        boolean[][] check = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    // 벽이 없는 경우
                    if ((board[i][j] & (1 << k)) == 0) continue;
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    // 벽을 허물면 성 밖인 경우
                    if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                    // 이미 체크한 곳인 경우
                    if (check[nx][ny]) continue;
                    // 벽을 허문 곳이 같은 방인 경우
                    if (room[i][j] == room[nx][ny]) continue;
                    maxCombineArea = Math.max(maxCombineArea, roomArea[room[i][j]] + roomArea[room[nx][ny]]);
                }
                check[i][j] = true;
            }
        }

        System.out.println(roomCnt);
        System.out.println(maxArea);
        System.out.println(maxCombineArea);
    }

    // room[x][y] 에 방 번호를 마킹하며 방의 넓이를 구한다.
    private static int dfs(int x, int y) {
        room[x][y] = roomCnt;
        int area = 1;
        for (int i = 0; i < 4; i++) {
            // 벽으로 막혀있는 경우
            if ((board[x][y] & (1 << i)) > 0) continue;
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 이미 방문한 경우
            if (room[nx][ny] > 0) continue;
            area += dfs(nx, ny);
        }
        return area;
    }
}