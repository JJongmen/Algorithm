class Solution {
    fun solution(number: IntArray): Int {
        return (0 until number.size - 2).flatMap { i -> 
            (i + 1 until number.size - 1).flatMap { j -> 
                (j + 1 until number.size).map { k ->
                    Triple(number[i], number[j], number[k])
                }
            }
        }.count { (a, b, c) -> a + b + c == 0 }
    }
}