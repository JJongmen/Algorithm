import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] universes = new int[100][10000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                universes[i][j] = Integer.parseInt(st.nextToken());
            }
            compress(universes[i]);
        }

        int result = 0;
        for (int i = 0; i < M - 1; i++) {
            for (int j = i + 1; j < M; j++) {
                result += compare(i, j);
            }
        }
        System.out.println(result);
    }

    private static int compare(int i, int j) {
        for (int k = 0; k < N; k++) {
            if (universes[i][k] != universes[j][k]) {
                return 0;
            }
        }
        return 1;
    }

    static void compress(int[] planets) {
        Set<Integer> treeSet = new TreeSet<>();
        for (int planet : planets) {
            treeSet.add(planet);
        }
        List<Integer> sortList = new ArrayList<>();
        sortList.addAll(treeSet);
        for (int i = 0; i < N; i++) {
            planets[i] = Collections.binarySearch(sortList, planets[i]);
        }
    }
}