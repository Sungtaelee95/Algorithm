import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val p = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val gameRoom = Array(p) { mutableListOf<Player>() }

    repeat(p) {
        st = StringTokenizer(br.readLine())
        val player = Player(st.nextToken().toInt(), st.nextToken())
        for (room in gameRoom) {
            if (room.isEmpty()) {
                room.add(player)
                break
            }

            if (room.size >= m) continue

            if (player.level in room.first().level - 10..room.first().level + 10) {
                room.add(player)
                break
            }
        }
    }

    for (room in gameRoom) {
        if (room.isEmpty()) continue
        if (room.size == m) bw.appendLine("Started!")
        if (room.size < m) bw.appendLine("Waiting!")
        room.sortedBy { it.nickName }.forEach { player->
            bw.appendLine("${player.level} ${player.nickName}")
        }
    }

    bw.flush()
    bw.close()
}

data class Player(val level: Int, val nickName: String)
