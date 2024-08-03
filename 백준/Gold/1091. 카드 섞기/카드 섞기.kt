import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val card = Array<Info>(n) { Info(st.nextToken().toInt(), it) }
    st = StringTokenizer(br.readLine())
    val suf = IntArray(n) { st.nextToken().toInt() }
    var count = 0
    val tempCard = Array<Info>(n) { Info(card[it].playNum, card[it].cardNum) }
    while (true) {
        var flag = true
        tempCard.forEach {
            if (it.cardNum % 3 != it.playNum) {
                flag = false
            }
        }
        if (flag) break

        count++
        val temp = IntArray(n)
        // 셔플
        for (i in 0 until n) {
            temp[i] = tempCard[suf[i]].cardNum
        }
        // 셔플한 카드 순서 저장
        for (i in 0 until n) {
            tempCard[i].cardNum = temp[i]
        }
        // 순서가 동일하게 되었다면 만들 수 없다.
        if (tempCard.contentEquals(card)) {
            count = -1
            break
        }
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}

data class Info(val playNum: Int, var cardNum: Int)