class Solution {
    val openBrackets = setOf('(', '[', '{')
    val closeMap = mapOf(')' to '(', ']' to '[', '}' to '{')
    
    fun isRight(s: String): Boolean {
        val stack = mutableListOf<Char>()
        s.forEach { ch ->
            if (ch in openBrackets) {
                stack += ch
            } else {
                if (stack.lastOrNull() == closeMap[ch]!!) {
                    stack.removeLast()
                } else {
                    return false
                }
            }
        }
        return stack.isEmpty()
    }
    
    fun solution(s: String): Int {
        val stretchS = s + s
        return (0..s.lastIndex).count { isRight(stretchS.slice(it until it + s.length)) }
    }
}