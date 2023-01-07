import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        arr[0] = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new int[N * 4];
        init(1, 1, N);

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int v = Integer.parseInt(st.nextToken());
                update(1, 1, N, i, v);
            } else {
                int j = Integer.parseInt(st.nextToken());
                bw.write(getMinIdx(1, 1, N, i, j) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getMinIdx(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        int li = getMinIdx(lc, start, mid, left, right);
        int ri = getMinIdx(rc, mid + 1, end, left, right);
        if (arr[li] <= arr[ri]) {
            return li;
        }
        return ri;
    }

    private static void update(int node, int start, int end, int index, int val) {
        if (index < start || end < index) return;
        if (start == end) {
            arr[index] = val;
            tree[node] = index;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        update(lc, start, mid, index, val);
        update(rc, mid + 1, end, index, val);
        if (arr[tree[lc]] <= arr[tree[rc]]) {
            tree[node] = tree[lc];
        } else {
            tree[node] = tree[rc];
        }
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        if (arr[tree[lc]] <= arr[tree[rc]]) {
            tree[node] = tree[lc];
        } else {
            tree[node] = tree[rc];
        }
    }
}