import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] A = new int[50];
    static int[] B = new int[50];
    static boolean[] isPrime = new boolean[2001];
    static List<Integer>[] graph = new List[25];
    static int[] matched = new int[25];
    static boolean[] visit = new boolean[25];

    static {
        // 2000까지의 소수를 구함
        Arrays.fill(isPrime, 2, 2001, true);
        for (int i = 1; i * i <= 2000; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= 2000; j += i) {
                isPrime[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // A와 B 그룹으로 홀수, 짝수 그룹을 나눔.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idxA = 0;
        int idxB = 0;
        A[idxA++] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N - 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (A[0] % 2 == num % 2) {
                A[idxA++] = num;
            } else {
                B[idxB++] = num;
            }
        }
        if (idxA != idxB) {
            System.out.println(-1);
            return;
        }

        // 두 그룹 사이의 연결 가능한 소수 쌍들을 구한다.
        for (int i = 0; i < N / 2; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 0; j < N / 2; j++) {
                if(!isPrime[A[i] + B[j]]) continue;
                graph[i].add(j);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int nxt : graph[0]) {
            Arrays.fill(matched, 0, N / 2, -1);
            matched[nxt] = 0;
            int cnt = 1;
            for (int cur = 1; cur < N / 2; cur++) {
                Arrays.fill(visit, false);
                if (dfs(cur)) cnt++;
            }
            if (cnt == N / 2) result.add(B[nxt]);
        }
        Collections.sort(result);
        if (result.isEmpty()) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : result) {
            sb.append(num).append(' ');
        }
        System.out.println(sb);
    }

    private static boolean dfs(int cur) {
        if (cur == 0) return false;
        for (int nxt : graph[cur]) {
            if (visit[nxt]) continue;
            visit[nxt] = true;
            // A그룹의 cur과 B그룹의 nxt를 매칭가능하면
            if (matched[nxt] == -1 || dfs(matched[nxt])) {
                matched[nxt] = cur;
                return true;
            }
        }
        return false;
    }
}