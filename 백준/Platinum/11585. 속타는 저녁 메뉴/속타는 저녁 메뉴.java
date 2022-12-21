import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] failure(String s) {
        int[] f = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = f[j - 1];
            if (s.charAt(i) == s.charAt(j)) f[i] = ++j;
        }
        return f;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String roulette = br.readLine().replace(" ", "");
        roulette += roulette.substring(0, N - 1);
        String meat = br.readLine().replace(" ", "");

        int[] f = failure(meat);
        int j = 0;
        int cnt = 0;
        for (int i = 0; i < roulette.length(); i++) {
            while (j > 0 && roulette.charAt(i) != meat.charAt(j)) j = f[j - 1];
            if (roulette.charAt(i) == meat.charAt(j)) j++;
            if (j == N) {
                cnt++;
                j = f[j - 1];
            }
        }

        int gcd = gcd(N, cnt);
        System.out.println(cnt / gcd + "/" + N / gcd);
    }

    private static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
}