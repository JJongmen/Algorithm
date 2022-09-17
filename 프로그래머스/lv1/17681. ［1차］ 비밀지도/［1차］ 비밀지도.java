class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        StringBuilder sb;
        for (int i = 0; i < n; i++) {
            sb = new StringBuilder();
            String binString = Integer.toBinaryString(arr1[i] | arr2[i]);
            for (int j = 0; j < binString.length(); j++) {
                if (binString.charAt(j) == '1') {
                    sb.append('#');
                } else {
                    sb.append(" ");
                }
            }
            while (sb.length() < n) {
                sb.insert(0, ' ');
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}