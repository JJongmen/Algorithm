import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[][] notebook;

    /**
     * 1. 스티커를 회전시키지 않은 상태로 가장 왼쪽 위부터 붙여본다.
     * 2. 가장 왼쪽 위에 붙지 않는다면 한칸씩 오른쪽으로 간다.
     * 3. 만약 맨 오른쪽까지 가도 붙일 수 없다면 한칸 아랫줄의 맨 왼쪽줄에 붙여본다.
     * 4. 위 2~3 과정을 반복해도 붙일 수 없다면 스티커를 시계방향으로 90도 회전시킨다.
     * 5. 위 4번 과정을 스티커가 붙을 떄까지 최대 270도까지 회전시키고 그래도 안붙는다면 버린다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        notebook = new int[N][M];
        int K = Integer.parseInt(st.nextToken());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 회전시키지 않았을 때
            for (int i = 0; i < 4; i++) {
                if (putSticker(sticker)) break;
                if (i != 3) sticker = turnSticker(sticker);
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1) cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static int[][] turnSticker(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] result = new int[C][R];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                result[j][R - 1 - i] = sticker[i][j];
            }
        }
        return result;
    }

    private static boolean putSticker(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        for (int i = 0; i <= N - R; i++) {
            for (int j = 0; j <= M - C; j++) {
                if (locationToPutSticker(i, j, sticker)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean locationToPutSticker(int x, int y, int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] = notebook[x + i][y + j];
            }
        }
        for (int i = x; i < x + R; i++) {
            for (int j = y; j < y + C; j++) {
                if (sticker[i - x][j - y] == 1) {
                    if (notebook[i][j] == 1) {
                        rollback(x, y, temp);
                        return false;
                    }
                    notebook[i][j] = 1;
                }
            }
        }
        return true;
    }

    private static void rollback(int x, int y, int[][] temp) {
        int R = temp.length;
        int C = temp[0].length;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                notebook[x + i][y + j] = temp[i][j];
            }
        }
    }
}