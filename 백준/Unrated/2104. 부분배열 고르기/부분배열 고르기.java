import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr = new int[100001];
    static SumAndMinIdx[] tree = new SumAndMinIdx[400001];

    static class SumAndMinIdx {
        long sum;
        int minIdx;

        public SumAndMinIdx(long sum, int minIdx) {
            this.sum = sum;
            this.minIdx = minIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, N);
        arr[0] = 1000005;
        System.out.println(getMaxScore(1, N));
    }

    private static long getMaxScore(int left, int right) {
        if (left == right) {
            return (long) arr[left] * arr[left];
        }
        int minIdx = getMinIdx(1, 1, N, left, right);
        long score = sum(1, 1, N, left, right) * arr[minIdx];
        if (left <= minIdx - 1) {
            score = Math.max(score, getMaxScore(left, minIdx - 1));
        }
        if (minIdx + 1 <= right) {
            score = Math.max(score, getMaxScore(minIdx + 1, right));
        }
        return score;
    }

    private static long sum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node].sum;
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return sum(lc, start, mid, left, right) + sum(rc, mid + 1, end, left, right);
    }

    private static int getMinIdx(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node].minIdx;
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        int lIdx = getMinIdx(lc, start, mid, left, right);
        int rIdx = getMinIdx(rc, mid + 1, end, left, right);
        if (arr[lIdx] < arr[rIdx]) return lIdx;
        return rIdx;
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = new SumAndMinIdx(arr[start], start);
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        int minIdx = 0;
        if (arr[tree[lc].minIdx] < arr[tree[rc].minIdx]) {
            minIdx = tree[lc].minIdx;
        } else {
            minIdx = tree[rc].minIdx;
        }
        tree[node] = new SumAndMinIdx(tree[lc].sum + tree[rc].sum, minIdx);
    }
}