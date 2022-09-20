import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] lab = new int[N][M];
        Queue<Position> virusQue = new LinkedList<>();
        List<Position> emptyAreaList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 0) {
                    emptyAreaList.add(new Position(i, j));
                }
                if (lab[i][j] == 2) {
                    virusQue.offer(new Position(i, j));
                }
            }
        }

        int maxSafeArea = 0;
        for (int i = 0; i < emptyAreaList.size() - 2; i++) {
            Position firstWall = emptyAreaList.get(i);
            lab[firstWall.x][firstWall.y] = 1;
            for (int j = i + 1; j < emptyAreaList.size() - 1; j++) {
                Position secondWall = emptyAreaList.get(j);
                lab[secondWall.x][secondWall.y] = 1;
                for (int k = j + 1; k < emptyAreaList.size(); k++) {
                    Position thirdWall = emptyAreaList.get(k);
                    lab[thirdWall.x][thirdWall.y] = 1;
                    int[][] tempLab = new int[N][M];
                    for (int m = 0; m < tempLab.length; m++) {
                        tempLab[m] = lab[m].clone();
                    }
                    Queue<Position> tempVirusQue = new LinkedList<>();
                    tempVirusQue.addAll(virusQue);
                    maxSafeArea = Math.max(maxSafeArea, bfs(tempLab, tempVirusQue));
                    lab[thirdWall.x][thirdWall.y] = 0;
                }
                lab[secondWall.x][secondWall.y] = 0;
            }
            lab[firstWall.x][firstWall.y] = 0;
        }

        System.out.println(maxSafeArea);
    }

    private static boolean check(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static int bfs(int[][] tempLab, Queue<Position> tempVirusQue) {
        while (!tempVirusQue.isEmpty()) {
            Position virus = tempVirusQue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = virus.x + dx[i];
                int nextY = virus.y + dy[i];
                if (check(nextX, nextY) && tempLab[nextX][nextY] == 0) {
                    tempLab[nextX][nextY] = 2;
                    tempVirusQue.offer(new Position(nextX, nextY));
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempLab[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}