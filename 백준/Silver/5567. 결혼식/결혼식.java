import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static Set<Integer> result = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        List<Integer> friends = graph.get(1);
        result.addAll(friends);
        for (int friend : friends) {
            result.addAll(graph.get(friend));
        }
        System.out.println(result.size() == 0 ? 0 : result.size() - 1);
    }
}