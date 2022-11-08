import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int S, E, Q;
    static Set<String> enters = new HashSet<>();
    static int result;

    static int toMinutes(String time) {
        String[] tokens = time.split(":");
        return Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = toMinutes(st.nextToken());
        E = toMinutes(st.nextToken());
        Q = toMinutes(st.nextToken());
        while (br.ready()) {
            st = new StringTokenizer(br.readLine());
            int minutes = toMinutes(st.nextToken());
            String name = st.nextToken();
            if (minutes <= S) {
                enters.add(name);
            } else if (E <= minutes && minutes <= Q && enters.remove(name)) {
                result++;
            }
            if (Q < minutes) {
                break;
            }
        }
        System.out.println(result);
    }
}