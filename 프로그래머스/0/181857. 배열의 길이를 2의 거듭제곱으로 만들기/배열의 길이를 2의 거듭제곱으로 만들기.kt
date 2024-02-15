class Solution {
    fun solution(arr: IntArray): IntArray {
        var size = 1
        while (size < arr.size) {
            size *= 2
        }
        return arr + IntArray(size - arr.size)
    }
}