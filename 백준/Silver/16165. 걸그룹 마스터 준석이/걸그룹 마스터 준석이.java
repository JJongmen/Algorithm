import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Map<String, List<String>> groupToMembers = new HashMap<>();
    static Map<String, String> memberToGroup = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            String group = br.readLine();
            List<String> members = new ArrayList<>();
            int memberCnt = Integer.parseInt(br.readLine());
            for (int i = 0; i < memberCnt; i++) {
                String member = br.readLine();
                memberToGroup.put(member, group);
                members.add(member);
            }
            Collections.sort(members);
            groupToMembers.put(group, members);
        }

        while (M-- > 0) {
            String key = br.readLine();
            if (Integer.parseInt(br.readLine()) == 1) {
                bw.write(memberToGroup.get(key) + "\n");
            } else {
                List<String> members = groupToMembers.get(key);
                for (String member : members) {
                    bw.write(member + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}