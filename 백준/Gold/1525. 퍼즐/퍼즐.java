import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static final String STABLE = "123456780";
    static int[] d = {-1, 1, -3, 3};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start.append(scan.nextInt());
            }
        }

        Queue<String> que = new LinkedList<>();
        HashMap<String, Integer> visit = new HashMap<>();
        que.offer(start.toString());
        visit.put(start.toString(), 0);
        while (!que.isEmpty()) {
            String cur = que.poll();
            if (cur.equals(STABLE)) break;
            int idx = findBlankIdx(cur);
            for (int i = 0; i < 4; i++) {
                if (i == 0 && idx % 3 == 0) continue;
                if (i == 1 && idx % 3 == 2) continue;
                int nxtIdx = idx + d[i];
                if (nxtIdx < 0 || nxtIdx >= 9) continue;
                String nxt = swap(cur, idx, nxtIdx);
                if (visit.containsKey(nxt)) continue;
                que.offer(nxt);
                visit.put(nxt, visit.get(cur) + 1);
            }
        }
        System.out.println(visit.getOrDefault(STABLE, -1));
    }

    private static String swap(String original, int a, int b) {
        StringBuilder next = new StringBuilder(original);
        next.setCharAt(a, original.charAt(b));
        next.setCharAt(b, original.charAt(a));
        return next.toString();
    }

    private static int findBlankIdx(String cur) {
        for (int i = 0; i < 9; i++) {
            if (cur.charAt(i) == '0') {
                return i;
            }
        }
        return -1;
    }
}