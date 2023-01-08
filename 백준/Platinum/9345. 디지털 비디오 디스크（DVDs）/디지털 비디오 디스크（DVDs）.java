import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int T, N, K;
    static int[] arr;
    static Node[] tree;

    static class Node {
        int min, max;

        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = i;
            }
            tree = new Node[N * 4];
            init(1, 0, N - 1);

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int Q = Integer.parseInt(st.nextToken());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                if (Q == 0) {
                    swap(A, B);
                } else {
                    bw.write(canRent(1, 0, N - 1, A, B) ? "YES\n" : "NO\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(start, end);
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        tree[node] = new Node(tree[lc].min, tree[rc].max);
    }

    private static boolean canRent(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return true;
        if (left <= start && end <= right) {
            return left <= tree[node].min && tree[node].max <= right;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return canRent(lc, start, mid, left, right) && canRent(rc, mid + 1, end, left, right);
    }

    private static void swap(int a, int b) {
        int tmp = arr[a];
        update(1, 0, N - 1, a, arr[b]);
        update(1, 0, N - 1, b, tmp);
    }

    private static void update(int node, int start, int end, int index, int val) {
        if (index < start || end < index) return;
        if (start == end) {
            arr[index] = tree[node].min = tree[node].max = val;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        update(lc, start, mid, index, val);
        update(rc, mid + 1, end, index, val);
        tree[node].min = Math.min(tree[lc].min, tree[rc].min);
        tree[node].max = Math.max(tree[lc].max, tree[rc].max);
    }
}