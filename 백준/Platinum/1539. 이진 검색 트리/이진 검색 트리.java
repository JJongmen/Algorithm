import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {
    static int N;
    static int[] P = new int[250003];
    static TreeMap<Integer, Integer> heightVertexs = new TreeMap<>();
    static long result = 0;

    static class Vertex {
        int num;
        int height;

        public Vertex(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }
    static void insert(int X) {
        Entry<Integer, Integer> rightParentEntry = heightVertexs.higherEntry(X);
        Entry<Integer, Integer> leftParentEntry = heightVertexs.lowerEntry(X);
        int leftHeight = 0;
        int rightHeight = 0;
        if (leftParentEntry != null) {
            leftHeight = leftParentEntry.getValue();
        }
        if (rightParentEntry != null) {
            rightHeight = rightParentEntry.getValue();
        }
        heightVertexs.put(X, Math.max(leftHeight, rightHeight) + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(br.readLine());
        }

        heightVertexs.put(P[0], 1);
        for (int i = 1; i <= N - 1; i++) {
            insert(P[i]);
        }

        for (Integer height : heightVertexs.values()) {
            result += height;
        }
        System.out.println(result);
    }
}