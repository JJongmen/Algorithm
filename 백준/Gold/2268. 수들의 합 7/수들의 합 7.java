import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        tree = new long[N * 4];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            if (type == 0) {
                int j = Integer.parseInt(st.nextToken());
                if (i <= j) {
                    bw.write(sum(1, 1, N, i, j) + "\n");
                } else {
                    bw.write(sum(1, 1, N, j, i) + "\n");
                }
            } else {
                int k = Integer.parseInt(st.nextToken());
                modify(1, 1, N, i, k);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void modify(int node, int start, int end, int index, int val) {
        if (index < start || end < index) return;
        if (start == end) {
            arr[index] = val;
            tree[node] = val;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        modify(lc, start, mid, index, val);
        modify(rc, mid + 1, end, index, val);
        tree[node] = tree[lc] + tree[rc];
    }

    private static long sum(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0L;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return sum(lc, start, mid, left, right) + sum(rc, mid + 1, end, left, right);
    }

}