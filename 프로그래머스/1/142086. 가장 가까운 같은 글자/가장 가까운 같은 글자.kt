class Solution {
    fun solution(s: String): IntArray {
        return s.withIndex().map { (index, ch) -> 
            s.substring(0 until index).lastIndexOf(ch).let {
                if (it == -1) -1 else index - it
            }
        }.toIntArray()
    }
}