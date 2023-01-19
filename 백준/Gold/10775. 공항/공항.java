import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int G, P;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parents = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parents[i] = i;
        }

        int result = 0;
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
            g = find(g);
            if (g == 0) break;
            union(g - 1, g);
            result++;
        }
        System.out.println(result);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parents[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}