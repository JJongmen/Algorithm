import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int G = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i < G; i++) {
            if (G % i != 0) continue;
            if ((G / i + i) % 2 != 0) continue;
            list.add((G / i + i) / 2);
        }
        if (list.isEmpty()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append('\n');
        }
        System.out.println(sb);
    }
}