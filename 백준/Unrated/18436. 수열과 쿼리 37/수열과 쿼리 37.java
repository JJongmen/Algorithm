import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr = new int[100001];
    static int[] tree = new int[100000 * 4 + 1];    // 홀수의 갯수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, 1, N);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int i = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (arr[i] % 2 == x % 2) continue;
                arr[i] = x;
                if (x % 2 == 0) update(1, 1, N, i, -1);
                else update(1, 1, N, i, 1);
            } else {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int oddCnt = query(1, 1, N, l, r);
                if (type == 2) {
                    bw.write(r - l + 1 - oddCnt + "\n");
                } else {
                    bw.write(oddCnt + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int query(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return query(lc, start, mid, left, right) + query(rc, mid + 1, end, left, right);
    }

    private static void update(int node, int start, int end, int idx, int diff) {
        if (end < idx || idx < start) return;
        tree[node] += diff;
        if (start == end) return;
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        update(lc, start, mid, idx, diff);
        update(rc, mid + 1, end, idx, diff);
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start] % 2;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        tree[node] = tree[lc] + tree[rc];
    }
}
