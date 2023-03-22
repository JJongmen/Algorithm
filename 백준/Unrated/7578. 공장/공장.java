import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] pos = new int[1000001];
    static int[] arr = new int[500001];
    static long[] tree = new long[500000 * 4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pos[Integer.parseInt(st.nextToken())] = i;
        }

        long result = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = pos[Integer.parseInt(st.nextToken())];
            update(1, 1, N, arr[i]);
            if (arr[i] == N) continue;
            result += query(1, 1, N, arr[i] + 1, N);
        }
        System.out.println(result);
    }

    private static long query(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return query(lc, start, mid, left, right) + query(rc, mid + 1, end, left, right);
    }

    private static void update(int node, int start, int end, int index) {
        if (index < start || end < index) {
            return;
        }

        tree[node]++;

        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index);
            update(node * 2 + 1, mid + 1, end, index);
        }
    }
}