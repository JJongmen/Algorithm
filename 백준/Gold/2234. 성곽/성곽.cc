#include <bits/stdc++.h>
using namespace std;

int N, M;
int board[50][50];
int room[50][50];
int roomArea[2501];
int dx[4] = {0, -1, 0, 1};
int dy[4] = {-1, 0, 1, 0};
int roomCnt;

int dfs(int x, int y) {
	room[x][y] = roomCnt;
	int area = 1;
	for (int i = 0; i < 4; i++) {
		if ((board[x][y] & (1 << i)) > 0) continue;
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (room[nx][ny]) continue;
		area += dfs(nx, ny);
	}
	return area;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];
		}
	}
	
	int maxArea = 0;
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (room[i][j]) continue;
			roomCnt++;
			roomArea[roomCnt] = dfs(i, j);
			maxArea = max(maxArea, roomArea[roomCnt]);
		}
	}		
	
	int maxCombineArea = maxArea;
	bool check[M][N];
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			check[i][j] = false;
		}
	}
	
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < 4; k++) {
				if (!(board[i][j] & (1 << k))) continue;
				int nx = i + dx[k];
				int ny = j + dy[k];
				if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
				if (check[nx][ny]) continue;
				if (room[i][j] == room[nx][ny]) continue;
				maxCombineArea = max(maxCombineArea, roomArea[room[i][j]] + roomArea[room[nx][ny]]);
			}
			check[i][j] = true;
		}
	}
	
	cout << roomCnt << '\n' << maxArea << '\n' << maxCombineArea;
}