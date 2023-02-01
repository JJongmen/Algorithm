import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M, C;
    static int[][] box; // box[i][j] = i번 마을에서 j번 마을로 배송될 수 있는 박스의 개수
    static int[] to;    // to[i] = 트럭에 실린 i번 마을에 배송될 박스의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        box = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            box[a][b] += c;
        }

        to = new int[N + 1];
        TreeSet<Integer> destinations = new TreeSet<>();
        int cur = 0;    // 현재 트럭에 실린 박스의 개수
        int result = 0;
        for (int i = 1; i < N; i++) {
            // i번 마을에 트럭에 실린 박스를 내린다.
            cur -= to[i];
            result += to[i];

            // 목적지 마을이 가까운 순부터 트럭에 박스를 싣는다.
            for (int j = i + 1; j <= N; j++) {
                if (box[i][j] == 0) continue;
                if (cur + box[i][j] <= C) {
                    to[j] += box[i][j];
                    cur += box[i][j];
                } else {
                    int maxLoadCnt = Math.min(C, box[i][j]);
                    int overLoadCnt = cur + maxLoadCnt - C;
                    while (!destinations.isEmpty() && overLoadCnt > 0 && j < destinations.last()) {
                        if (to[destinations.last()] > overLoadCnt) {
                            to[destinations.last()] -= overLoadCnt;
                            overLoadCnt = 0;
                        } else {
                            overLoadCnt -= to[destinations.last()];
                            to[destinations.last()] = 0;
                            destinations.pollLast();
                        }
                    }
                    if (maxLoadCnt >= overLoadCnt) {
                        to[j] += maxLoadCnt - overLoadCnt;
                        destinations.add(j);
                        cur = C;
                    }
                }
            }
        }
        result += to[N];

        System.out.println(result);
    }
}