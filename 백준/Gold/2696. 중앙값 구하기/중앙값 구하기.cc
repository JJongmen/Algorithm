#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(0);
	int T;
	cin >> T;
	while (T-- > 0) {
		int M;
		cin >> M;
		cout << M / 2 + 1 << '\n';
		priority_queue<int, vector<int>, greater<int> > minPq;
		priority_queue<int> maxPq;
		for (int i = 0; i < M / 10; i++) {
			for (int j = 0; j < 10; j++) {
				int num;
				cin >> num;
				if (j % 2 == 0) {
					maxPq.push(num);
				} else {
					minPq.push(num);
				}
				if (!minPq.empty() && maxPq.top() > minPq.top()) {
					minPq.push(maxPq.top());
					maxPq.pop();
					maxPq.push(minPq.top());
					minPq.pop();
				}
				if (j % 2 == 0) {
					cout << maxPq.top() << ' ';
				}
			}
			if (i % 2 == 1) {
				cout << '\n';
			}
		}
		for (int i = 0; i < M % 10; i++) {
			int num;
			cin >> num;
			if (i % 2 == 0) {
				maxPq.push(num);
			} else {
				minPq.push(num);
			}
			if (!minPq.empty() && maxPq.top() > minPq.top()) {
				minPq.push(maxPq.top());
				maxPq.pop();
				maxPq.push(minPq.top());
				minPq.pop();
			}
			if (i % 2 == 0) {
				cout << maxPq.top() << ' ';
			}
		}
		cout << '\n';
	}
	return 0;
}