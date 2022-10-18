import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int NON_BLOCK = -2;
    static int N, M;
    static int[][] board = new int[25][25];
    static boolean[][] visit;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static List<Info> infos;

    static class Info {
        Position pos;   // 기준 블록의 좌표
        List<Position> blocks;  // 블록들의 좌표 리스트
        int rainbowCnt; // 무지개 블록의 개수

        public Info(Position pos, List<Position> blocks, int rainbowCnt) {
            this.pos = pos;
            this.blocks = blocks;
            this.rainbowCnt = rainbowCnt;
        }
    }

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static int autoPlay() {
        int result = 0;
        // 1. 크기가 가장 큰 블록 그룹을 찾는다.
        while (findBlockGroup()) {
            // 2. 1번 과정에서 크기가 가장 큰 블록 그룹이 있다면 해당 블록들을 전부 제거한다.
            List<Position> blocks = infos.get(0).blocks;
            for (Position block : blocks) {
                board[block.x][block.y] = NON_BLOCK;
            }
            // 3. 점수를 획득한다.
            result += blocks.size() * blocks.size();
            // 4. 중력을 작용한다.
            gravitate();
            // 5. 반시계 방향으로 90도 회전시킨다.
            rotate();
            // 6. 중력을 작용한다.
            gravitate();
        }
        return result;
    }

    static boolean findBlockGroup() {
        infos = new ArrayList<>();
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 아직 방문하지 않았으며 일반 블록인 경우 블록 그룹을 찾는다.
                if (board[i][j] > 0 && !visit[i][j]) {
                    grouping(i, j);
                }
            }
        }
        // 블록 그룹이 없는 경우
        if (infos.size() == 0) {
            return false;
        }
        // 블록 그룹이 있는 경우 크기가 가장 큰 블록 그룹이 인덱스가 0인 위치에 있도록 infos 리스트를 정렬한다.
        infos.sort(((o1, o2) -> o2.pos.y - o1.pos.y));
        infos.sort(((o1, o2) -> o2.pos.x - o1.pos.x));
        infos.sort((o1, o2) -> o2.rainbowCnt - o1.rainbowCnt);
        infos.sort((o1, o2) -> o2.blocks.size() - o1.blocks.size());
        return true;
    }

    static void grouping(int sx, int sy) {
        int color = board[sx][sy];
        List<Position> rainbows = new ArrayList<>();
        List<Position> blocks = new ArrayList<>();
        Queue<Position> que = new LinkedList<>();
        visit[sx][sy] = true;
        Position start = new Position(sx, sy);
        que.offer(start);
        blocks.add(start);
        while (!que.isEmpty()) {
            Position cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visit[nx][ny]) continue;
                if (board[nx][ny] != color && board[nx][ny] != 0) continue;
                visit[nx][ny] = true;
                Position next = new Position(nx, ny);
                blocks.add(next);
                que.offer(next);
                if (board[nx][ny] == 0) rainbows.add(next);
            }
        }
        if (blocks.size() > 1) {
            infos.add(new Info(start, blocks, rainbows.size()));
        }
        // 다른 블록그룹의 탐색을 위해 무지개 블록의 방문 기록을 지워줌
        for (Position cur : rainbows) {
            visit[cur.x][cur.y] = false;
        }
    }

    static void gravitate() {
        for (int j = 0; j < N; j++) {
            Queue<Integer> que = new LinkedList<>();
            int floor = N;
            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] >= 0) {
                    que.offer(board[i][j]);
                } else if (board[i][j] == -1) {
                    int idx = floor - 1;
                    while (!que.isEmpty()) {
                        board[idx--][j] = que.poll();
                    }
                    while (idx > i) {
                        board[idx--][j] = NON_BLOCK;
                    }
                    floor = i;
                }
            }
            while (!que.isEmpty()) {
                int idx = floor - 1;
                while (!que.isEmpty()) {
                    board[idx--][j] = que.poll();
                }
                while (idx >= 0) {
                    board[idx--][j] = NON_BLOCK;
                }
            }
        }
    }

    static void rotate() {
        int[][] tmpBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[N - 1 - j][i] = tmpBoard[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(autoPlay());
    }
}