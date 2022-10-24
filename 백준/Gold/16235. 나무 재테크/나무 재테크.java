import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] A = new int[11][11];
    static int[][] nutrients = new int[11][11];     // 각 칸의 양분
    static List<Integer>[][] trees = new List[11][11];    // 각 칸마다 나무의 나이들을 저장한다.
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                nutrients[i][j] = 5;
                trees[i][j] = new ArrayList<>();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x][y].add(z);
        }

        while (K-- > 0) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 봄
                    trees[i][j].sort(Integer::compareTo);
                    List<Integer> afterTrees = new ArrayList<>();
                    int dieNutrients = 0;
                    for (Integer age : trees[i][j]) {
                        if (age > nutrients[i][j]) {
                            dieNutrients += age / 2;
                            continue;
                        }
                        nutrients[i][j] -= age;
                        int nxtAge = age + 1;
                        afterTrees.add(nxtAge);
                    }
                    trees[i][j] = afterTrees;
                    // 여름
                    nutrients[i][j] += dieNutrients;
                }
            }
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 가을
                    for (Integer age : trees[i][j]) {
                        if (age % 5 == 0) {
                            for (int k = 0; k < 8; k++) {
                                int nx = i + dx[k];
                                int ny = j + dy[k];
                                if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                                trees[nx][ny].add(1);
                            }
                        }
                    }
                    // 겨울
                    nutrients[i][j] += A[i][j];
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result += trees[i][j].size();
            }
        }
        System.out.println(result);
    }
}