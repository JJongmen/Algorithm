class Solution {
    fun solution(id_pw: Array<String>, db: Array<Array<String>>): String {
        db.firstOrNull { it[0] == id_pw[0] }?.let {
            return if (it[1] == id_pw[1]) "login" else "wrong pw"
        }
        return "fail"
    }
}