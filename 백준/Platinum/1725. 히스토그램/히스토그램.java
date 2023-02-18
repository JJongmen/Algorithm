import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int INF = 1_000_000_005;
    static int N;
    static int[] heights = new int[100001];
    static int[] tree = new int[1 << 18];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        heights[0] = INF;
        for (int i = 1; i <= N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        init(1, 1, N);
        System.out.println(getMaxArea(1, N));
    }

    private static int getMaxArea(int left, int right) {
        if (right < left) {
            return 0;
        }
        int idx = getMinHeightIdx(1, 1, N, left, right);
        return Math.max(Math.max(getMaxArea(left, idx - 1), getMaxArea(idx + 1, right)),
                heights[idx] * (right - left + 1));
    }

    // [start, end] : tree[node] 에 저장된 범위
    // [left, right] : 구하려는 범위
    private static int getMinHeightIdx(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        int lIdx = getMinHeightIdx(lc, start, mid, left, right);
        int rIdx = getMinHeightIdx(rc, mid + 1, end, left, right);
        if (heights[lIdx] <= heights[rIdx]) {
            return lIdx;
        }
        return rIdx;
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
        if (heights[tree[lc]] <= heights[tree[rc]]) {
            tree[node] = tree[lc];
            return;
        }
        tree[node] = tree[rc];
    }
}