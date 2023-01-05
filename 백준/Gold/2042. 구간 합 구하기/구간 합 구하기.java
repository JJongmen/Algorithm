import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] nums = new long[1000001];
    static long[] tree = new long[1 << 21];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        init(1, 1, N);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                update(b, c);
            } else {
                bw.write(query(1, 1, N, b, c) + "\n");
            }
        }
        bw.flush();
    }

    private static void update(int index, long val) {
        long diff = val - nums[index];
        nums[index] = val;
        update_tree(1, 1, N, index, diff);
    }

    private static void update_tree(int node, int start, int end, int index, long diff) {
        if (index < start || index > end) return;
        tree[node] += diff;
        if (start != end) {
            update_tree(node * 2, start, (start + end) / 2, index, diff);
            update_tree(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
        }
    }

    private static long query(int node, int start, int end, int left, long right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && right >= end) {
            return tree[node];
        }
        long lSum = query(node * 2, start, (start + end) / 2, left, right);
        long rSum = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return lSum + rSum;
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}