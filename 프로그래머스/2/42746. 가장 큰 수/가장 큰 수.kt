class Solution {
    fun solution(numbers: IntArray): String {
        var numStrs = numbers.map { it.toString() }.toTypedArray()
        val comparator = Comparator<String> { a, b -> (b+a).compareTo(a+b) }
        numStrs.sortWith(comparator)
        if (numStrs[0] == "0" && numStrs[numStrs.size - 1] == "0") {
            return "0"
        }
        return numStrs.joinToString("")
    }
}