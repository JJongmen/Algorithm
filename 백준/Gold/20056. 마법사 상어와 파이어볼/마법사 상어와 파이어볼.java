import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<List<FireBall>>> board = new ArrayList<>();
    static List<FireBall> fireballs = new ArrayList<>();
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static class FireBall {
        int x, y;   // 위치
        int m, s, d;    // 질량, 속력, 방향

        public FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public String toString() {
            return "FireBall{" +
                    "x=" + x +
                    ", y=" + y +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boardInit(N);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            FireBall fb = new FireBall(r, c, m, s, d);
            fireballs.add(fb);
            board.get(r).get(c).add(fb);
        }

        while (K-- > 0) {
            // 모든 파이어볼을 이동시킨다.
            for (FireBall fb : fireballs) {
                int nx = fb.x + (dx[fb.d] * fb.s) % N;
                int ny = fb.y + (dy[fb.d] * fb.s) % N;
                board.get(fb.x).get(fb.y).remove(fb);
                if (nx < 1) nx += N;
                if (nx > N) nx -= N;
                if (ny < 1) ny += N;
                if (ny > N) ny -= N;
                fb.x = nx;
                fb.y = ny;
                board.get(nx).get(ny).add(fb);
            }

            // 2개 이상의 파이어볼이 있는 칸에 대해 합치고 나눈다.
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    List<FireBall> place = board.get(i).get(j);
                    int size = place.size();
                    if (size < 2) continue;
                    int sumM = 0;
                    int sumS = 0;
                    int prevD = -1;
                    boolean sameFlag = true;
                    for (FireBall fb : place) {
                        sumM += fb.m;
                        sumS += fb.s;
                        if (prevD == -1) prevD = fb.d;
                        else {
                            sameFlag &= (prevD % 2) == (fb.d % 2);
                        }
                        fireballs.remove(fb);
                    }
                    place.clear();
                    int nxtM = Math.floorDiv(sumM, 5);
                    if (nxtM == 0) continue;
                    if (sameFlag) {
                        for (int d = 0; d <= 6; d += 2) {
                            FireBall fb = new FireBall(i, j, nxtM, Math.floorDiv(sumS, size), d);
                            fireballs.add(fb);
                            place.add(fb);
                        }
                    } else {
                        for (int d = 1; d <= 7; d += 2) {
                            FireBall fb = new FireBall(i, j, nxtM, Math.floorDiv(sumS, size), d);
                            fireballs.add(fb);
                            place.add(fb);
                        }
                    }
                }
            }
        }

        int result = 0;
        for (FireBall fb : fireballs) {
            result += fb.m;
        }
        System.out.println(result);
    }

    private static void boardInit(int N) {
        for (int i = 0; i <= N; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j <= N; j++) {
                board.get(i).add(new ArrayList<>());
            }
        }
    }
}