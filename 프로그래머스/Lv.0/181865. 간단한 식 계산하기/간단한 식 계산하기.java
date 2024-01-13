class Solution {
    public int solution(String binomial) {
        String[] tokens = binomial.split(" ");
        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[2]);
        if (tokens[1].equals("+")) return a + b;
        if (tokens[1].equals("-")) return a - b;
        return a * b;
    }
}