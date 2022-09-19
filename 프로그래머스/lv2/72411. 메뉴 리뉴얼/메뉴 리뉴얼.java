import java.util.*;

class Solution {
    private static List<Map<Set<Character>, Integer>> courses = new ArrayList<>();
    private static int[] courseKinds;

    private static void dfs(int idx, String menu, Set<Character> comb) {
        if (comb.size() >= 2) {
            for (int i = 0; i < courseKinds.length; i++) {
                if (comb.size() == courseKinds[i]) {
                    courses.get(i).put(comb, courses.get(i).getOrDefault(comb, 0) + 1);
                    break;
                }
            }
        }
        if (idx == menu.length()) {
            return;
        }
        Set<Character> temp = null;
        for (int i = idx; i < menu.length(); i++) {
            temp = new TreeSet<>();
            temp.addAll(comb);
            temp.add(menu.charAt(i));
            dfs(i + 1, menu, temp);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        courseKinds = course;
        for (int i = 0; i <= course.length; i++) {
            courses.add(new HashMap<>());
        }
        for (String order : orders) {
            dfs(0, order, new TreeSet<>());
        }

        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i <= course.length; i++) {
            int max = 0;
            for (int cnt : courses.get(i).values()) {
                max = Math.max(max, cnt);
            }
            for (Set<Character> menuSet : courses.get(i).keySet()) {
                if (max >= 2 && courses.get(i).get(menuSet) == max) {
                    StringBuilder sb = new StringBuilder();
                    for (char ch : menuSet) {
                        sb.append(ch);
                    }
                    pq.offer(sb.toString());
                }
            }
        }
        String[] answer = new String[pq.size()];
        int k = 0;
        while (!pq.isEmpty()) {
            answer[k++] = pq.poll();
        }
        return answer;
    }
}