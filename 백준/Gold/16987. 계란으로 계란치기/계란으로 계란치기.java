import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] durability = new int[8];
    static int[] weights = new int[8];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            durability[i] = Integer.parseInt(st.nextToken());
            weights[i] = Integer.parseInt(st.nextToken());
        }

        breakEgg(0);
        System.out.println(result);
    }

    private static void breakEgg(int hand) {
        if (hand == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (durability[i] > 0) continue;
                cnt++;
            }
            result = Math.max(result, cnt);
            return;
        }

        if (durability[hand] <= 0) {
            breakEgg(hand + 1);
            return;
        }

        boolean hitFlag = false;
        for (int other = 0; other < N; other++) {
            if (durability[other] <= 0 || other == hand) continue;
            hitFlag = true;
            durability[other] -= weights[hand];
            durability[hand] -= weights[other];
            breakEgg(hand + 1);
            durability[other] += weights[hand];
            durability[hand] += weights[other];
        }

        if (!hitFlag) {
            breakEgg(hand + 1);
        }
    }
}