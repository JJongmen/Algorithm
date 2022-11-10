import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, Q;
    static TreeSet<Integer> places = new TreeSet<>();
    static int cur = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int A = Integer.parseInt(st.nextToken());
            if (A == 1) {
                places.add(i);
            }
        }
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            if (query == 1) {
                int i = Integer.parseInt(st.nextToken());
                if (places.contains(i)) {
                    places.remove(i);
                } else {
                    places.add(i);
                }
            } else if (query == 2) {
                int x = Integer.parseInt(st.nextToken());
                cur = (cur + x - 1) % N + 1;
            } else if (query == 3) {
                if (places.isEmpty()) {
                    bw.write("-1");
                } else {
                    Integer next = places.ceiling(cur);
                    if (next == null) {
                        bw.write(String.valueOf(N + places.first() - cur));
                    } else {
                        bw.write(String.valueOf(next - cur));
                    }
                }
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}