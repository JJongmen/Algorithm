class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        val alphabets = ('a'..'z').filter { it !in skip }
        return s.toList().map {
            alphabets[(alphabets.indexOf(it) + index) % alphabets.size]
        }.joinToString("")
    }
}