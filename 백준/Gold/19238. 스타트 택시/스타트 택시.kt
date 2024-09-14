import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var map: Array<IntArray>
var n = 0
var flag = true
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val initGas = st.nextToken().toInt()
    map = Array(n) { IntArray(n) }
    repeat(n) {
        st = StringTokenizer(br.readLine())
        map[it] = IntArray(n) { st.nextToken().toInt() }
    }
    st = StringTokenizer(br.readLine())
    val taxi = Taxi(st.nextToken().toInt() - 1, st.nextToken().toInt() - 1, initGas)
    val clients = mutableListOf<Client>()
    repeat(m) {
        st = StringTokenizer(br.readLine())
        clients.add(
            Client(
                st.nextToken().toInt() - 1,
                st.nextToken().toInt() - 1,
                st.nextToken().toInt() - 1,
                st.nextToken().toInt() - 1
            )
        )
    }

    while (true) {
        taxi.findClient(clients)
        if (!flag) break
        taxi.pickUp()
        taxi.goTargetPoint()
        if (!flag) break
        clients.remove(taxi.getCompleteTarget())
        if (clients.isEmpty()) break
    }

    if (!flag) {
        bw.append("-1")
    } else {
        bw.append("${taxi.gas}")
    }

    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int, val cnt: Int)
data class Position(val row: Int, val col: Int)
data class Client(val srow: Int, val scol: Int, val erow: Int, val ecol: Int)

val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)

class Taxi(var row: Int, var col: Int, var gas: Int) {
    var target = Client(5_000_001, 5_000_001, 5_000_001, 5_000_001)
    var minDist = Int.MAX_VALUE

    fun getCompleteTarget(): Client = target
    fun goTargetPoint() {
        val useGas = caluDist(Position(target.erow, target.ecol))
        if (useGas == Int.MAX_VALUE) {
            flag = false
            return
        }
        row = target.erow
        col = target.ecol
        gas -= useGas
        if (gas < 0) flag = false
        gas += useGas * 2
    }

    fun pickUp() {
        row = target.srow
        col = target.scol
        gas -= minDist
    }

    fun findClient(clients: MutableList<Client>) {
        minDist = Int.MAX_VALUE
        target = Client(5_000_001, 5_000_001, 5_000_001, 5_000_001)
        for (client in clients) {
            val nowDist = caluDist(Position(client.srow, client.scol))
            if (minDist == nowDist) {
                if (target.srow == client.srow) {
                    if (target.scol > client.scol) {
                        target = client
                        minDist = nowDist
                        continue
                    }
                }
                if (target.srow > client.srow) {
                    target = client
                    minDist = nowDist
                    continue
                }
            }
            if (minDist > nowDist) {
                target = client
                minDist = nowDist
            }
        }
        if (minDist > gas || minDist == Int.MAX_VALUE) flag = false
    }

    private fun caluDist(target: Position): Int {
        val visit = Array(n) { BooleanArray(n) }
        var dist = Int.MAX_VALUE
        val dq = ArrayDeque<Node>()
        dq.add(Node(row, col, 0))
        visit[row][col] = true
        while (dq.isNotEmpty()) {
            val now = dq.removeFirst()
            if (target.row == now.row && target.col == now.col) {
                dist = now.cnt
                break
            }
            for (i in 0..3) {
                val nr = now.row + rows[i]
                val nc = now.col + cols[i]
                if (nr < 0 || nc < 0 || nr >= n || nc >= n || visit[nr][nc] || map[nr][nc] == 1) continue
                visit[nr][nc] = true
                dq.add(Node(nr, nc, now.cnt + 1))
            }
        }
        return dist
    }
}
