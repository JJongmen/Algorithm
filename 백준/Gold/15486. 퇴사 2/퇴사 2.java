import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static class Consulting {
        int start;
        int end;
        int price;

        public Consulting(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Consulting> consultingList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int end = i + Integer.parseInt(st.nextToken());
            if (end <= N + 1) {
                consultingList.add(new Consulting(i, end, Integer.parseInt(st.nextToken())));
            }
        }

        consultingList.sort(((o1, o2) -> o1.end - o2.end));
        int[] memo = new int[N + 2];
        int maxIdx = 0;
        for (Consulting consulting : consultingList) {
            if (maxIdx < consulting.end) {
                for (int i = maxIdx + 1; i <= consulting.end; i++) {
                    memo[i] = memo[maxIdx];
                }
            }
            memo[consulting.end] = Math.max(memo[consulting.end],
                    memo[consulting.start] + consulting.price);
            if (memo[maxIdx] <= memo[consulting.end]) {
                maxIdx = consulting.end;
            }
        }

        System.out.println(memo[maxIdx]);
    }
}