import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int INF = 10001;
    private static List<Position> chickenList;
    private static List<Position> houseList;
    private static int M;
    private static int cityChickenDist = INF;

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int houseKind = Integer.parseInt(st.nextToken());
                if (houseKind == 1) {
                    houseList.add(new Position(i, j));
                } else if (houseKind == 2) {
                    chickenList.add(new Position(i, j));
                }
            }
        }

        bitmasking(0, 0, 0);
        System.out.println(cityChickenDist);

    }

    private static void bitmasking(int chicken, int idx, int cnt) {
        if (cnt == M) {
            int cityDist = 0;
            for (Position house : houseList) {
                int minDist = INF;
                for (int i = 0; (1 << i) <= chicken; i++) {
                    if ((chicken & 1 << i) != 0) {
                        minDist = Math.min(minDist, Math.abs(chickenList.get(i).x - house.x) + Math.abs(chickenList.get(i).y - house.y));
                    }
                }
                cityDist += minDist;
            }
            cityChickenDist = Math.min(cityChickenDist, cityDist);
            return;
        }
        if (idx == chickenList.size()) {
            return;
        }
        bitmasking(chicken, idx + 1, cnt);
        bitmasking(chicken | (1 << idx), idx + 1, cnt + 1);
    }
}