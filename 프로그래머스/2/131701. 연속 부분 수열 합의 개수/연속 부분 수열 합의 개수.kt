class Solution {
    fun solution(elements: IntArray): Int {
        val sumSet = mutableSetOf<Int>()
        val extendedElements = elements + elements

        for (length in 1..elements.size) {
            var windowSum = extendedElements.sliceArray(0 until length).sum()
            sumSet.add(windowSum)

            for (start in 1..elements.size) {
                windowSum -= extendedElements[start - 1]
                windowSum += extendedElements[start + length - 1]
                sumSet.add(windowSum)
            }
        }

        return sumSet.size
    }
}