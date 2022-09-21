class Solution {
    private static final int ATTACK = 1;
    private static final int HEAL = 2;

    public int solution(int[][] board, int[][] skill) {
        // skill의 각 행은 [type, r1, c1, r2, c2, degree]

        int[][] temp = new int[board.length][board[0].length];
        for (int[] skillArray : skill) {
            int type = skillArray[0];
            int r1 = skillArray[1];
            int c1 = skillArray[2];
            int r2 = skillArray[3];
            int c2 = skillArray[4];
            int degree = skillArray[5];
            if (type == ATTACK) {
                degree *= -1;
            }
            temp[r1][c1] += degree;
            if (c2 + 1 < board[0].length) {
                temp[r1][c2 + 1] -= degree;
            }
            if (r2 + 1 < board.length) {
                temp[r2 + 1][c1] -= degree;
            }
            if (c2 + 1 < board[0].length && r2 + 1 < board.length) {
                temp[r2 + 1][c2 + 1] += degree;
            }
        }
        int answer = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 1; j < temp[0].length; j++) {
                temp[i][j] += temp[i][j - 1];
            }
        }
        for (int j = 0; j < temp[0].length; j++) {
            for (int i = 1; i < temp.length; i++) {
                temp[i][j] += temp[i - 1][j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += temp[i][j];
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}