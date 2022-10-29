import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> sortList = new ArrayList<>();

    static int lowerBound(int target) {
        int st = 0;
        int en = sortList.size();
        while (st < en) {
            int mid = (st + en) / 2;
            if (sortList.get(mid) >= target) en = mid;
            else st = mid + 1;
        }
        return st;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Set<Integer> set = new TreeSet<>();
        set.addAll(list);
        sortList.addAll(set);
        for (int i = 0; i < N; i++) {
            bw.write(lowerBound(list.get(i)) + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}