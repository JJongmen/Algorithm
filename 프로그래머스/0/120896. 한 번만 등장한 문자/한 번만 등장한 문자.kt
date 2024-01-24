class Solution {
    fun solution(s: String): String {
        return s.groupingBy { it }.eachCount().filter { it.value == 1 }.map { it.key }.sorted().joinToString("")
    }
}