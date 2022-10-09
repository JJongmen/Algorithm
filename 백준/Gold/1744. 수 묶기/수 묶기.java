import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        boolean zero = false;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                positives.add(num);
            } else if (num < 0) {
                negatives.add(num);
            } else {
                zero = true;
            }
        }
        positives.sort(Integer::compareTo);
        negatives.sort(Integer::compareTo);

        int result = 0;
        int pSize = positives.size();
        for (int i = pSize - 1; i >= 1; i -= 2) {
            int a = positives.get(i);
            int b = positives.get(i - 1);
            if (a == 1 || b == 1) {
                result += a + b;
            } else {
                result += a * b;
            }
        }
        if (pSize % 2 == 1) result += positives.get(0);
        int nSize = negatives.size();
        for (int i = 0; i < nSize - 1; i += 2) {
            result += negatives.get(i) * negatives.get(i + 1);
        }
        if (nSize % 2 == 1 && !zero) {
            result += negatives.get(nSize - 1);
        }
        System.out.println(result);
    }
}