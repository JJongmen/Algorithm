import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] board = new char[13][13];
    static boolean[][][][] check = new boolean[13][13][13][13];
    static int N, M;

    static class Info {
        int rx, ry, bx, by, seq;

        public Info(int rx, int ry, int bx, int by, int seq) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.seq = seq;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int srx, sry, sbx, sby;
        srx = sry = sbx = sby = 0;
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j);
                if (board[i][j] == 'R') {
                    srx = i;
                    sry = j;
                } else if (board[i][j] == 'B') {
                    sbx = i;
                    sby = j;
                }
            }
        }

        Queue<Info> que = new LinkedList<>();
        check[srx][sry][sbx][sby] = true;
        que.offer(new Info(srx, sry, sbx, sby, 0));
        while (!que.isEmpty()) {
            Info cur = que.poll();
            if (cur.seq == 10) break;
            int rx = cur.rx;
            int ry = cur.ry;
            int bx = cur.bx;
            int by = cur.by;

            for (int i = 0; i < 4; i++) {
                boolean rFlag = false;  // 빨간 구슬이 구멍에 들어가면 true
                boolean bFlag = false;  // 파란 구슬이 구멍에 들어가면 true
                int nrx = rx;
                int nry = ry;
                int nbx = bx;
                int nby = by;

                while (board[nrx][nry] != '#') {
                    if (board[nrx][nry] == 'O') {
                        rFlag = true;
                        break;
                    }
                    nrx += dx[i];
                    nry += dy[i];
                }
                nrx -= dx[i];
                nry -= dy[i];

                while (board[nbx][nby] != '#') {
                    if (board[nbx][nby] == 'O') {
                        bFlag = true;
                        break;
                    }
                    nbx += dx[i];
                    nby += dy[i];
                }
                nbx -= dx[i];
                nby -= dy[i];

                // 빨간 구슬만 구멍에 들어가고 파란 구슬은 들어가지 않는다면 1을 출력 및 종료
                if (rFlag && !bFlag) {
                    System.out.println(cur.seq + 1);
                    return;
                }
                // 위 조건을 만족하지 않고 파란 구슬이 들어갔다면 더 탐색할 필요가 없음
                if (bFlag) continue;

                // 두 구슬이 같은 위치에 있다면 움직이기 전에 멀리 있던 구슬을 1만큼 되돌린다.
                if (nrx == nbx && nry == nby) {
                    int diffR = Math.abs(nrx - rx) + Math.abs(nry - ry);
                    int diffB = Math.abs(nbx - bx) + Math.abs(nby - by);
                    if (diffR > diffB) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }
                if (check[nrx][nry][nbx][nby]) continue;
                check[nrx][nry][nbx][nby] = true;
                que.offer(new Info(nrx, nry, nbx, nby, cur.seq + 1));
            }
        }

        System.out.println(-1);
    }
}