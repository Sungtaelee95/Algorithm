import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    var K = st.nextToken().toInt()

    val dq = ArrayDeque<Belt>()
    var result = 0

    st = StringTokenizer(br.readLine())
    repeat(2*N) {
        dq.addLast(Belt())
        dq[it].setHp(st.nextToken().toInt())
    }

    while (true) {
        result++
        dq.addFirst(dq.removeLast()) // 한 바퀴 돌리기
        if (dq[N-1].isUpRobot()) dq[N-1].downRobot()

        for (i in N-2 downTo 1) { // 로봇들 전진 시키기
            if (dq[i].isUpRobot() && dq[i+1].isPossibleUpRobot()) {
                dq[i].downRobot()
                dq[i+1].upRobot()
                if (dq[i+1].getHp() <= 0) K--
            }
        }
        if (dq[N-1].isUpRobot()) dq[N-1].downRobot()
        if (K <= 0) break

        if (dq[0].isPossibleUpRobot()) { // 로봇 집어 넣기
            dq[0].upRobot()
            if (dq[0].getHp() <= 0) K--
        }
        if (K <= 0) break
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

class Belt {
    private var robot = false
    private var hp = 0
    fun upRobot () {
        robot = true
        hp--
    }

    fun isPossibleUpRobot() = (hp > 0 && !robot)

    fun downRobot() {
        robot = false
    }

    fun isUpRobot() = robot

    fun setHp(value: Int) {
        hp = value
    }

    fun getHp() = hp
}