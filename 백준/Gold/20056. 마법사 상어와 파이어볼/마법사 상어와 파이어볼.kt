import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val directions = arrayOf(
    Node(-1, 0), // 0
    Node(-1, 1), // 1
    Node(0, 1), // 2
    Node(1, 1), // 3
    Node(1, 0), // 4
    Node(1, -1), // 5
    Node(0, -1), // 6
    Node(-1, -1) // 7
)

lateinit var map: Array<Array<MutableList<Fire>>>
var n = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    map = Array(n) { Array(n) { mutableListOf() } }
    repeat(m) {
        st = StringTokenizer(readLine())
        val fire = Fire(
            st.nextToken().toInt() - 1,
            st.nextToken().toInt() - 1,
            st.nextToken().toInt(),
            st.nextToken().toInt(),
            st.nextToken().toInt()
        )
        map[fire.r][fire.c].add(fire)
    }
    repeat(k) {
        val newMap = Array(n) { Array(n) { mutableListOf<Fire>() } }
        // 이동
        map.forEach { array ->
            array.forEach { list ->
                list.forEach { fire ->
                    fire.move()
                    newMap[fire.r][fire.c].add(fire)
                }
            }
        }
        // 이동이 끝난 뒤
        for (r in 0 until n) {
            for (c in 0 until n) {
                if (newMap[r][c].size >= 2) {
                    val list = newMap[r][c]
                    val sumFire = Fire(list[0].r, list[0].c, list.sumOf { it.m }, list.sumOf { it.s }, 0) // 방향 임시
                    val subFires = Array(4) { Fire(sumFire.r, sumFire.c, sumFire.m / 5, sumFire.s / list.size, 0) }
                    if (list.all { it.d % 2 == 0 } || list.all { it.d % 2 == 1 }) { // 방향이 모두 짝수 이거나 홀수라면
                        subFires.forEachIndexed { index, fire ->
                            fire.d = (index) * 2
                        }
                    } else { // 모두 짝수 혹은 홀수가 아닐 경우
                        subFires.forEachIndexed { index, fire ->
                            fire.d = (index) * 2 + 1
                        }
                    }
                    // 질량이 0 보다 높은 것들만 추가
                    newMap[r][c] = subFires.filter { it.m > 0 }.toMutableList()
                }
            }
        }
        // 맵 정보 갱신
        map = newMap
    }
    var result = 0
    map.forEach { array ->
        array.forEach { list ->
            result += list.sumOf { it.m }
        }
    }

    bw.append("$result")
    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int)
data class Fire(var r: Int, var c: Int, val m: Int, val s: Int, var d: Int) {
    fun move() {
        var nr = r + (directions[d].row * s) % n
        if (nr < 0) nr += n
        if (nr >= n) nr -= n
        var nc = c + (directions[d].col * s) % n
        if (nc < 0) nc += n
        if (nc >= n) nc -= n
        r = nr
        c = nc
    }
}