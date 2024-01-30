class Solution {
    fun solution(q: Int, r: Int, code: String): String {
        return code.withIndex().filter { (index, _) ->
            index % q == r
        }.map { it.value }.joinToString("")
    }
}