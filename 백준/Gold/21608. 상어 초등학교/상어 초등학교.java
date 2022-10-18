import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] classroom = new int[25][25];
    static int N;
    static int[][] favorites = new int[405][5];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Info {
        int x, y;   // 좌표
        int emptyCnt;   // 인접한 빈 칸의 개수
        int likeCnt;    // 인접한 칸에 좋아하는 학생들의 수

        public Info(int x, int y, int emptyCnt, int likeCnt) {
            this.x = x;
            this.y = y;
            this.emptyCnt = emptyCnt;
            this.likeCnt = likeCnt;
        }
    }

    // num 번호인 학생의 자리를 정한다.
    static void setSeat(int num) {
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] != 0) continue;
                int emptyCnt = 0;
                int likeCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    int seat = classroom[nx][ny];
                    if (seat == 0) emptyCnt++;
                    else {
                        for (int friend : favorites[num]) {
                            if (seat == friend) {
                                likeCnt++;
                                break;
                            }
                        }
                    }
                }
                infos.add(new Info(i, j, emptyCnt, likeCnt));
            }
        }
        infos.sort((o1, o2) -> o2.emptyCnt - o1.emptyCnt);
        infos.sort((o1, o2) -> o2.likeCnt - o1.likeCnt);
        Info seat = infos.get(0);
        classroom[seat.x][seat.y] = num;
    }

    // x, y 자리의 학생의 만족도를 반환한다.
    static int getSatisfaction(int x, int y) {
        int num = classroom[x][y];
        int likeCnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            int seat = classroom[nx][ny];
            for (int friend : favorites[num]) {
                if (seat == friend) {
                    likeCnt++;
                    break;
                }
            }
        }
        if (likeCnt == 0) return 0;
        if (likeCnt == 1) return 1;
        if (likeCnt == 2) return 10;
        if (likeCnt == 3) return 100;
        return 1000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                favorites[num][j] = Integer.parseInt(st.nextToken());
            }
            setSeat(num);
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += getSatisfaction(i, j);
            }
        }

        System.out.println(result);
    }
}