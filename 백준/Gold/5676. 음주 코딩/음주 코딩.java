import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int x = Integer.parseInt(st.nextToken());
                if (x > 0) x = 1;
                else if (x < 0) x = -1;
                arr[i] = x;
            }

            tree = new int[N * 4];
            init(1, 1, N);

            StringBuilder sb = new StringBuilder();
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int i = Integer.parseInt(st.nextToken());
                if ("C".equals(command)) {
                    int V = Integer.parseInt(st.nextToken());
                    change(1, 1, N, i, V);
                } else {
                    int j = Integer.parseInt(st.nextToken());
                    int result = product(1, 1, N, i, j);
                    if (result == 1) {
                        sb.append("+");
                    } else if (result == -1) {
                        sb.append("-");
                    } else {
                        sb.append("0");
                    }
                }
            }
            bw.write(sb.toString() + "\n");
            if (!br.ready()) break;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int product(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 1;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return product(lc, start, mid, left, right) * product(rc, mid + 1, end, left, right);
    }

    private static void change(int node, int start, int end, int index, int val) {
        if (index < start || end < index) return;
        if (start == end) {
            if (val > 0) val = 1;
            else if (val < 0) val = -1;
            tree[node] = val;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        change(lc, start, mid, index, val);
        change(rc, mid + 1, end, index, val);
        tree[node] = tree[lc] * tree[rc];
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            int num = arr[start];
            if (num > 0) num = 1;
            else if (num < 0) num = -1;
            tree[node] = num;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        tree[node] = tree[lc] * tree[rc];
    }
}