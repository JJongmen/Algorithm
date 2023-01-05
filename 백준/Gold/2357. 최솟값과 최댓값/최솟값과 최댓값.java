import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static final Info INF = new Info(Integer.MAX_VALUE, Integer.MIN_VALUE);
    static int N, M;
    static int[] arr;
    static Info[] tree;

    static class Info {
        int min, max;

        public Info(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new Info[1 << (h + 1)];
        init(1, 1, N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Info val = query(1, 1, N, a, b);
            bw.write(val.min + " " + val.max + "\n");
        }

        bw.flush();
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Info(arr[start], arr[start]);
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        Info lVal = tree[lc];
        Info rVal = tree[rc];
        tree[node] = new Info(Math.min(lVal.min, rVal.min), Math.max(lVal.max, rVal.max));
    }

    private static Info query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) return INF;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        Info lVal = query(lc, start, mid, left, right);
        Info rVal = query(rc, mid + 1, end, left, right);
        return new Info(Math.min(lVal.min, rVal.min), Math.max(lVal.max, rVal.max));
    }
}