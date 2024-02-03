class Solution {
    fun solution(code: String): String {
        var answer: String = ""
        var evenMode = true
        code.forEachIndexed { idx, ch ->
            if (ch == '1') evenMode = !evenMode
            else if ((evenMode && idx % 2 == 0) || (!evenMode && idx % 2 == 1)) answer += ch
        }
        return if (answer.isEmpty()) "EMPTY" else answer
    }
}