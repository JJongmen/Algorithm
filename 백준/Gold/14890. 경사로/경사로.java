import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = new int[205][105];
    static boolean[] placed = new boolean[105];
    static int N;
    static int L;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                map[j + N][i] = map[i][j];
            }
        }

        // 경사로가 설치되있으면 true
        for (int i = 0; i < 2 * N; i++) {
            Arrays.fill(placed, 0, N, false);
            checkRoad(i);
        }

        System.out.println(result);
    }

    private static void checkRoad(int i) {
        int prev = map[i][0];
        for (int j = 1; j < N; j++) {
            // 인접한 칸의 경사의 차이가 0과 1이 아닌 경우
            if (Math.abs(map[i][j] - prev) > 1) return;
            // 인접한 칸의 높이가 동일할 경우
            // 경사가 1 감소하는 경우
            else if (prev - 1 == map[i][j]) {
                for (int k = 0; k < L; k++) {
                    // 경사로를 놓다가 범위를 벗어나는 경우는 경사로를 놓을 수 없다.
                    if (j + k >= N) return;
                    if (map[i][j + k] == prev - 1 && !placed[j + k]) {
                        placed[j + k] = true;
                    } else {
                        return;
                    }
                }
            }
            // 경사가 1 증가하는 경우
            else if (prev + 1 == map[i][j]) {
                for (int k = 1; k <= L; k++) {
                    // 경사로를 놓다가 범위를 벗어나는 경우는 경사로를 놓을 수 없다.
                    if (j - k < 0) return;
                    if (map[i][j - k] == prev && !placed[j - k]) {
                        placed[j - k] = true;
                    } else {
                        return;
                    }
                }
            }
            prev = map[i][j];
        }
        result++;
    }
}