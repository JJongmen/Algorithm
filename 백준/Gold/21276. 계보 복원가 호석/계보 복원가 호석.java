import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    static int N, M;
    static List<String> names = new ArrayList<>();
    static Map<String, Integer> inDegree = new HashMap<>();
    static Map<String, List<String>> graph = new HashMap<>();
    static Set<String> founders = new TreeSet<>();
    static Map<String, Set<String>> childrens = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            names.add(name);
            inDegree.put(name, 0);
            graph.put(name, new LinkedList<>());
            childrens.put(name, new TreeSet<>());
        }
        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String descendant = st.nextToken();
            String ancestor = st.nextToken();
            graph.get(ancestor).add(descendant);
            inDegree.put(descendant, inDegree.getOrDefault(descendant, 0) + 1);
        }
    }

    private static void solve() {
        Queue<String> que = new LinkedList<>();
        for (String name : inDegree.keySet()) {
            if (inDegree.get(name) == 0) {
                founders.add(name);
                que.offer(name);
            }
        }
        while (!que.isEmpty()) {
            String cur = que.poll();
            for (String nxt : graph.get(cur)) {
                if (inDegree.put(nxt, inDegree.get(nxt) - 1) == 1) {
                    que.offer(nxt);
                    childrens.get(cur).add(nxt);
                }
            }
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(founders.size() + "\n");
        for (String founder : founders) {
            bw.write(founder + " ");
        }
        bw.newLine();
        for (String ancestor : childrens.keySet()) {
            Set<String> children = childrens.get(ancestor);
            bw.write(ancestor + " " + children.size() + " ");
            for (String child : children) {
                bw.write(child + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}