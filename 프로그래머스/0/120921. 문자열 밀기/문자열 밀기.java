class Solution {
    public int solution(String A, String B) {
        for (int i = 0; i < A.length(); i++) {
            if (B.equals(A.substring(A.length() - i, A.length()) + A.substring(0, A.length() - i))) return i;
        }
        return -1;
    }
}