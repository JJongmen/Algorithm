class Solution {
    fun solution(cipher: String, code: Int) = (code - 1 until cipher.length step code)
        .map { cipher[it] }
        .joinToString("")
}