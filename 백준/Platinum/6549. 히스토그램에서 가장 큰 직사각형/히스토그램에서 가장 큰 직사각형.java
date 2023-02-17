import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_RECTANGLE = 100000;
    public static final int INF = 1_000_000_005;

    static int N;
    static int[] heights = new int[MAX_RECTANGLE + 1];

    static int[] tree = new int[1 << 18];   // 구간 내 높이가 가장 작은 직사각형의 인덱스를 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            for (int i = 1; i <= N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }
            heights[0] = INF;
            minInit(1, 1, N);
            bw.write(getMaxArea(1, N) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static long getMaxArea(int left, int right) {
        if (right < left) {
            return 0;
        }
        if (left == right) {
            return heights[left];
        }
        long width = right - left + 1;
        int minIdx = getMinHeightIdx(1, 1, N, left, right);
        long area = width * heights[minIdx];
        return Math.max(area, Math.max(getMaxArea(left, minIdx - 1), getMaxArea(minIdx + 1, right)));
    }

    // minHeightTree[node] = [start, end] 범위의 최솟값인 높이의 idx
    private static void minInit(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        minInit(lc, start, mid);
        minInit(rc, mid + 1, end);
        if (heights[tree[lc]] <= heights[tree[rc]]) {
            tree[node] = tree[lc];
        } else {
            tree[node] = tree[rc];
        }
    }

    // node에 저장된 구간은 [start, end]
    // 최솟값을 구하는 구간은 [left, right]
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
}