import kotlin.math.*

class Solution {
    fun countDivisors(number: Int): Int {
        var count = 0
        var i = 1
        while (i * i < number) {
            if (number % i++ == 0) count += 2
        }
        if (i * i == number) count++
        return count
    }
    
    fun solution(number: Int, limit: Int, power: Int): Int {
        return (1..number).sumOf {
            val divisorCnt = countDivisors(it)
            if (divisorCnt > limit) power else divisorCnt
        }
    }
}