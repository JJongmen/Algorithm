import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<List<Integer>> partyPeople = new ArrayList<>();
    static List<List<Integer>> partyGraph = new ArrayList<>();
    static Queue<Integer> knows = new LinkedList<>();
    static boolean[] visitParty = new boolean[51];
    static boolean[] isKnow = new boolean[51];
    static int result;
    static {
        for (int i = 0; i <= 50; i++) {
            partyPeople.add(new LinkedList<>());
            partyGraph.add(new LinkedList<>());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            int person = Integer.parseInt(st.nextToken());
            isKnow[person] = true;
            knows.offer(person);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int person = Integer.parseInt(st.nextToken());
                partyPeople.get(i).add(person);
                partyGraph.get(person).add(i);
            }
        }

        while (!knows.isEmpty()) {
            Integer cur = knows.poll();
            // 한 사람이 방문 할 수 있는 모든 파티들을 순회한다.
            for (Integer party : partyGraph.get(cur)) {
                if (visitParty[party]) continue;
                visitParty[party] = true;
                result++;
                // 방문한 파티의 모든 사람들은 진실을 아는 사람들이 된다.
                for (Integer person : partyPeople.get(party)) {
                    if (isKnow[person]) continue;
                    knows.offer(person);
                    isKnow[person] = true;
                }
            }
        }
        System.out.println(M - result);
    }
}