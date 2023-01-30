import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Integer[] crane;
    static Integer[] box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        crane = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crane, Comparator.reverseOrder());

        M = Integer.parseInt(br.readLine());
        box = new Integer[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(box, Collections.reverseOrder());

        // 크레인의 최대 무게 제한보다 박스의 최대 무게가 더 나가는 경우
        if (crane[0] < box[0]) {
            System.out.println(-1);
            return;
        }

        boolean[] isLoaded = new boolean[M];
        int startBoxIdx = 0;
        int remainBoxCnt = M;
        int time = 0;
        for (; remainBoxCnt > 0; time++) {
            // 각각의 크레인에 박스를 배정
            for (int i = 0; i < N; i++) {
                Integer maxWeight = crane[i];
                // 현재 크레인의 무게 제한보다 박스의 무게가 더 나가면 더 가벼운 박스를 배정
                int idx = startBoxIdx;
                for (; idx < M; idx++) {
                    if (isLoaded[idx]) continue;
                    if (crane[i] >= box[idx]) break;
                }
                // 남은 상자의 무게보다 크레인의 무게제한이 더 작은 경우 남은 크레인 탐색 X
                if (idx == M) break;
                // 크레인에 박스를 배정한다.
                isLoaded[idx] = true;
                remainBoxCnt--;
            }
        }

        System.out.println(time);
    }
}