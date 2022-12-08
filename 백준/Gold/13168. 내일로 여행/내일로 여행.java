import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static final double INF = 1_000_000_000;
    static int N, R, M, K;
    static int cityCnt;
    static Map<String, Integer> cityNumbers = new HashMap<>();
    static String[] route;
    static double[][] dO, dX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String city = st.nextToken();
            if (cityNumbers.containsKey(city)) continue;
            cityNumbers.put(city, cityCnt++);
        }

        dO = new double[cityCnt][cityCnt];
        dX = new double[cityCnt][cityCnt];
        for (int i = 0; i < cityCnt; i++) {
            Arrays.fill(dO[i], INF);
            Arrays.fill(dX[i], INF);
            dO[i][i] = 0;
            dX[i][i] = 0;
        }

        M = Integer.parseInt(br.readLine());
        route = new String[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = st.nextToken();
        }
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            String S = st.nextToken();
            String E = st.nextToken();
            double cost = Double.parseDouble(st.nextToken());
            int a = findCityIndex(S);
            int b = findCityIndex(E);
            dX[a][b] = Math.min(dX[a][b], cost);
            dX[b][a] = Math.min(dX[b][a], cost);
            double discountCost = discount(cost, type);
            dO[a][b] = Math.min(dO[a][b], discountCost);
            dO[b][a] = Math.min(dO[b][a], discountCost);
        }

        for (int k = 0; k < cityCnt; k++) {
            for (int i = 0; i < cityCnt; i++) {
                for (int j = 0; j < cityCnt; j++) {
                    dX[i][j] = Math.min(dX[i][j], dX[i][k] + dX[k][j]);
                    dO[i][j] = Math.min(dO[i][j], dO[i][k] + dO[k][j]);
                }
            }
        }

        double totalCostX = 0;
        double totalCostO = R;
        for (int i = 0; i < M - 1; i++) {
            String start = route[i];
            String end = route[i + 1];
            int si = findCityIndex(start);
            int ei = findCityIndex(end);
            totalCostX += dX[si][ei];
            totalCostO += dO[si][ei];
        }
        System.out.println(totalCostX <= totalCostO ? "No" : "Yes");
    }

    private static int findCityIndex(String city) {
        return cityNumbers.get(city);
    }

    private static double discount(double cost, String type) {
        if (type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun") || type.equals("Mugunghwa")) {
            return 0D;
        } else if (type.equals("S-Train") || type.equals("V-Train")) {
            return cost / 2D;
        } else {
            return cost;
        }
    }
}