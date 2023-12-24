class Solution {
    fun solution(names: Array<String>, yearnings: IntArray, photoes: Array<Array<String>>): IntArray {
        val nameToYearning = names.zip(yearnings.toTypedArray()).toMap()
        return photoes.map { photo -> 
            photo.sumOf { nameToYearning[it] ?: 0 }
        }.toIntArray()
    }
}