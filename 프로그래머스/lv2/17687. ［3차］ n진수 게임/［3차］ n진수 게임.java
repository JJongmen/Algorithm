class Solution {
    public String solution(int n, int t, int m, int p) {
        int totalLength = (t - 1) * m + p;
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (sb.length() < totalLength) {
            sb.append(Integer.toString(num++, n));
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < t; i++) {
            ret.append(sb.charAt(p - 1 + m * i));
        }
        return ret.toString().toUpperCase();
    }
}