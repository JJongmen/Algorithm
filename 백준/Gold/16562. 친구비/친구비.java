import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N, M, k;
    static int[] p = new int[10001];

    static class Friend {
        int num;
        int cost;

        public Friend(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static {
        for (int i = 1; i <= 10000; i++) {
            p[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Friend[] friends = new Friend[N];
        for (int i = 0; i < N; i++) {
            friends[i] = new Friend(i + 1, Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (v == w) continue;
            if (find(v) == find(w)) continue;
            union(v, w);
            cnt++;
        }

        int minCost = 0;
        Arrays.sort(friends, Comparator.comparingInt(o -> o.cost));
        for (Friend friend : friends) {
            if (find(friend.num) == 0) continue;
            union(0, friend.num);
            minCost += friend.cost;
            if (++cnt == N) break;
        }

        System.out.println(minCost <= k ? minCost : "Oh no");
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            p[b] = a;
            return;
        }
        if (a > b) {
            p[a] = b;
        }
    }

    private static int find(int a) {
        if (a == p[a]) {
            return a;
        }
        return p[a] = find(p[a]);
    }
}