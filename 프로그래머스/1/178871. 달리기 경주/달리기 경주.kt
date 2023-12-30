class Solution {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val playerMap = mutableMapOf<String, Int>()
        players.withIndex().associateTo(playerMap) { (index, player) ->
            player to index
        }
        callings.forEach { calling ->
            val idx = playerMap[calling]!!
            playerMap[players[idx - 1]] = idx
            playerMap[players[idx]] = idx - 1
            players[idx - 1] = calling.also { players[idx] = players[idx - 1] }
        }
        return players
    }
}