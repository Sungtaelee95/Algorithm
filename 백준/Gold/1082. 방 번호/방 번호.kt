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
    val room = IntArray(n) { st.nextToken().toInt() }
    val money = br.readLine().toInt()
    var startNum = 0
    var len = 0
    var min = 51
    for (i in n - 1 downTo 0) {
        if (room[i] <= min) {
            startNum = i
            min = room[i]
            len = money / room[i]
        }
    }
    var dist = len * min
    val numbers = IntArray(len) { startNum }
    var index = 0
    for (i in numbers.indices) {
        var newPrice = 0
        for (j in n - 1 downTo 0) {
            newPrice = room[j]
            if (dist - min + newPrice <= money) {
                numbers[i] = j
                dist = dist - min + newPrice
                break
            }
        }
        if (numbers[index] == 0) {
            index++
            dist -= min
        }
    }

    if (index == len) {
        bw.append("0")
    } else {
        for (i in index until len) {
            bw.append("${numbers[i]}")
        }
    }
    bw.flush()
    bw.close()
}

