class Solution {
    fun solution(before: String, after: String): Int {
        return if (before.toCharArray().sorted() == after.toCharArray().sorted()) 1 else 0
    }
}