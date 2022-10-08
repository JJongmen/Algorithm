#include <iostream>
#include <string>
using namespace std;

int solve(int a) {
    if(a==1) return 1;
    else if(a==2) return 2;
    else if(a==3) return 4;
    else return solve(a-3) + solve(a-2) + solve(a-1);
}

int main() {
    int n, m;
    cin >> n;
    while(n--) {
        cin >> m;
        cout << solve(m) << endl;
    }
	return 0;
}