import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Q1450 {
    static int N, C;
    static int[] weights = new int[30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> lSum = new ArrayList<>();
        dfs(0, N / 2, 0, lSum);
        List<Long> rSum = new ArrayList<>();
        dfs(N / 2, N, 0, rSum);

        Collections.sort(rSum);
        int result = 0;
        for (Long sum : lSum) {
            result += upperBound(rSum, C - sum);
        }
        System.out.println(result);
    }

    private static int upperBound(List<Long> rSum, long target) {
        int l = 0;
        int r = rSum.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (rSum.get(mid) <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }


    private static void dfs(int cur, int end, long sum, List<Long> list) {
        if (cur == end) {
            list.add(sum);
            return;
        }
        dfs(cur + 1, end, sum, list);
        dfs(cur + 1, end, sum + weights[cur], list);
    }
}
