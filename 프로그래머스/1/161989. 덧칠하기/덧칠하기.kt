class Solution {
    fun solution(n: Int, m: Int, section: IntArray): Int =
        section.fold(0 to 0) { (count, last), num -> 
            if (last < num) (count + 1) to (num + m - 1)
            else count to last
        }.first
}