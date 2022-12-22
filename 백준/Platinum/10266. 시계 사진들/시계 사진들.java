import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX = 360000;
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] clock = new boolean[MAX * 2];
        for (int i = 0; i < N; i++) {
            int hand = Integer.parseInt(st.nextToken());
            clock[hand] = clock[hand + MAX] = true;
        }

        st = new StringTokenizer(br.readLine());
        boolean[] other = new boolean[MAX];
        for (int i = 0; i < N; i++) {
            int hand = Integer.parseInt(st.nextToken());
            other[hand] = true;
        }

        int[] f = failure(other);
        int j = 0;
        for (int i = 0; i < clock.length; i++) {
            while (j > 0 && clock[i] != other[j]) j = f[j - 1];
            if (clock[i] == other[j]) j++;
            if (j == MAX) {
                System.out.println("possible");
                return;
            }
        }
        System.out.println("impossible");
    }

    private static int[] failure(boolean[] other) {
        int[] f = new int[other.length];
        int j = 0;
        for (int i = 1; i < other.length; i++) {
            while (j > 0 && other[i] != other[j]) j = f[j - 1];
            if (other[i] == other[j]) f[i] = ++j;
        }
        return f;
    }
}