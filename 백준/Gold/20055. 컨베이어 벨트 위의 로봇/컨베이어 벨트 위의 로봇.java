import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] durabilities = new int[200];
    static boolean[] hasRobot = new boolean[200];

    static class Robot {
        int pos;

        public Robot(int pos) {
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            durabilities[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        int turn = 0;
        int loadPos = 0;  // 올리는 위치
        int unLoadPos = N - 1; // 내리는 위치
        Queue<Robot> que = new LinkedList();
        while (cnt < K) {
            // 1. 벨트 회전
            loadPos = (loadPos - 1 + 2 * N) % (2 * N);
            unLoadPos = (unLoadPos - 1 + 2 * N) % (2 * N);
            if (hasRobot[unLoadPos]) hasRobot[unLoadPos] = false;
            // 2. 먼저 올라간 로봇부터 이동할 수 있다면 이동
            int size = que.size();
            while (size-- > 0) {
                Robot robot = que.poll();
                // 이미 내린 경우는 제외
                if (!hasRobot[robot.pos]) continue;
                int nxtPos = (robot.pos + 1) % (2 * N);
                // 로봇을 이동시킬 수 없는 경우
                if (hasRobot[nxtPos] || durabilities[nxtPos] == 0) {
                    que.offer(new Robot(robot.pos));
                } else {
                    hasRobot[robot.pos] = false;
                    if (--durabilities[nxtPos] == 0) cnt++;
                    // 이동하려는 위치가 내리는 위치이면 로봇을 내린다.
                    if (nxtPos == unLoadPos) continue;
                    hasRobot[nxtPos] = true;
                    que.offer(new Robot(nxtPos));
                }
            }
            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다
            if (durabilities[loadPos] != 0) {
                if (--durabilities[loadPos] == 0) cnt++;
                que.offer(new Robot(loadPos));
                hasRobot[loadPos] = true;
            }
            turn++;
        }
        System.out.println(turn);
    }
}