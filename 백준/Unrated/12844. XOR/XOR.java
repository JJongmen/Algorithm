import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr = new int[500000];
    static int[] tree = new int[2000000];
    static int[] lazy = new int[2000000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, 0, N - 1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int k = Integer.parseInt(st.nextToken());
                update_range(1, 0, N - 1, i, j, k);
            } else {
                bw.write(xor(1, 0, N - 1, i, j) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        tree[node] = tree[lc] ^ tree[rc];
    }

    private static void update_range(int node, int start, int end, int left, int right, int value) {
        update_lazy(node, start, end);
        if (end < left || right < start) return;
        int lc = node * 2;
        int rc = lc + 1;
        if (left <= start && end <= right) {
            tree[node] ^= value * ((end - start + 1) % 2);
            if (start != end) {
                lazy[lc] ^= value;
                lazy[rc] ^= value;
            }
            return;
        }
        int mid = (start + end) / 2;
        update_range(lc, start, mid, left, right, value);
        update_range(rc, mid + 1, end, left, right, value);
        tree[node] = tree[lc] ^ tree[rc];
    }

    private static void update_lazy(int node, int start, int end) {
        if (lazy[node] == 0) return;
        tree[node] ^= lazy[node] * ((end - start + 1) % 2);
        if (start != end) {
            lazy[node * 2] ^= lazy[node];
            lazy[node * 2 + 1] ^= lazy[node];
        }
        lazy[node] = 0;
    }

    private static int xor(int node, int start, int end, int left, int right) {
        update_lazy(node, start, end);
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        return xor(lc, start, mid, left, right) ^ xor(rc, mid + 1, end, left, right);
    }
}