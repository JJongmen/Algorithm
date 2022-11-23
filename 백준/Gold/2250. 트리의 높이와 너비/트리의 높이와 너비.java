import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] lc, rc, p;
    static int x = 1;
    static List<Integer>[] tree;
    static int minLevel = 1;
    static int maxWidth = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());
            lc[num] = leftChild;
            rc[num] = rightChild;
            if (leftChild != -1) {
                p[leftChild] = num;
            }
            if (rightChild != -1) {
                p[rightChild] = num;
            }
        }
        int root = 1;
        for (int i = 1; i <= N; i++) {
            if (p[i] == 0) {
                root = i;
                break;
            }
        }
        dfs(root, 1);
        int level = 1;
        while (!tree[level].isEmpty()) {
            int width = tree[level].get(tree[level].size() - 1) - tree[level].get(0) + 1;
            if (width > maxWidth) {
                maxWidth = width;
                minLevel = level;
            }
            level++;
        }
        System.out.println(minLevel + " " + maxWidth);
    }

    private static void init() {
        lc = new int[N + 1];
        rc = new int[N + 1];
        p = new int[N + 1];
        tree = new List[N + 2];
        for (int i = 0; i < N + 2; i++) {
            tree[i] = new ArrayList<>();
        }
    }

    private static void dfs(int cur, int level) {
        if (lc[cur] != -1) {
            dfs(lc[cur], level + 1);
        }
        tree[level].add(x++);
        if (rc[cur] != -1) {
            dfs(rc[cur], level + 1);
        }
    }
}