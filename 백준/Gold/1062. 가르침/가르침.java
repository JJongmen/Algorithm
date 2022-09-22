import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int K;
    private static int[] words;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new int[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                words[i] |= (1 << word.charAt(j) - 'a');
            }
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }
        int bitmask = 0;
        bitmask |= (1 << (int) 'a' - 'a');
        bitmask |= (1 << (int) 'n' - 'a');
        bitmask |= (1 << (int) 't' - 'a');
        bitmask |= (1 << (int) 'i' - 'a');
        bitmask |= (1 << (int) 'c' - 'a');
        comb(0, 5, bitmask);
        System.out.println(max);
    }

    private static void comb(int idx, int cnt, int bitmask) {
        if (cnt == K) {
            int ret = 0;
            for (int word : words) {
                if ((word & bitmask) == word) {
                    ret++;
                }
            }
            max = Math.max(max, ret);
            return;
        }
        if (idx == 26) {
            return;
        }
        comb(idx + 1, cnt, bitmask);
        if ((bitmask & (1 << idx)) == 0) {
            comb(idx + 1, cnt + 1, bitmask | (1 << idx));
        }
    }
}