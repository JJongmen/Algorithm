class Solution {
    public int solution(int[] num_list) {
        int oddSum = 0;
        int evenSum = 0;
        for (int i = 0; i < num_list.length; i += 2) {
            oddSum += num_list[i];
            if (i + 1 < num_list.length) evenSum += num_list[i + 1];
        }
        return Math.max(oddSum, evenSum);
    }
}