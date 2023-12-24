class Solution {
    fun solution(names: Array<String>, yearnings: IntArray, photos: Array<Array<String>>): IntArray {
        val nameToYearning = (0 until names.size).associate { names[it] to yearnings[it] }
        return photos.map { photo -> 
            photo.map { name ->
                nameToYearning[name] ?: 0
            }.sum()
        }.toIntArray()
    }
}