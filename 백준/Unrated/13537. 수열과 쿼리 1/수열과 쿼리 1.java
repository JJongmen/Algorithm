import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr = new int[100001];
    static List<Integer>[] tree = new List[400001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        init(1, 1, N);

        M = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            bw.write(countBigger(1, 1, N, i, j, k) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int countBigger(int node, int start, int end, int left, int right, int value) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node].size() - upperBound(node, value);
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        return countBigger(lc, start, mid, left, right, value) + countBigger(rc, mid + 1, end, left, right, value);
    }

    private static int upperBound(int node, int value) {
        int l = 0;
        int r = tree[node].size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (tree[node].get(mid) <= value) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = new ArrayList<>();
            tree[node].add(arr[start]);
            return;
        }
        int mid = (start + end) / 2;
        int lc = node * 2;
        int rc = lc + 1;
        init(lc, start, mid);
        init(rc, mid + 1, end);
        tree[node] = merge(tree[lc], tree[rc]);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> mergeList = new ArrayList<>();
        int l = 0;
        int r = 0;
        int lSize = left.size();
        int rSize = right.size();
        while (l < lSize && r < rSize) {
            if (left.get(l) < right.get(r)) mergeList.add(left.get(l++));
            else mergeList.add(right.get(r++));
        }
        while (l < lSize) {
            mergeList.add(left.get(l++));
        }
        while (r < rSize) {
            mergeList.add(right.get(r++));
        }
        return mergeList;
    }
}