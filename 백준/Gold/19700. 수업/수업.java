import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int N;
    static List<Student> students = new ArrayList<>();
    static TreeMap<Integer, Integer> teams = new TreeMap<>();
    static int result;

    static class Student {
        int height;
        int minRank;

        public Student(int height, int minRank) {
            this.height = height;
            this.minRank = minRank;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            students.add(new Student(h, k));
        }

        students.sort((s1, s2) -> s2.height - s1.height);
        for (Student student : students) {
            Integer key = teams.lowerKey(student.minRank);
            if (key == null) {
                teams.put(1, teams.getOrDefault(1, 0) + 1);
            } else {
                if (teams.put(key, teams.get(key) - 1) == 1) {
                    teams.remove(key);
                }
                teams.put(key + 1, teams.getOrDefault(key + 1, 0) + 1);
            }
        }

        for (Integer value : teams.values()) {
            result += value;
        }
        System.out.println(result);
    }
}