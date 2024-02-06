import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()

    val highArr = IntArray(1001) { 0 }
    var highIndex = 0
    var highValue = 0

    repeat(N) {
        st = StringTokenizer(br.readLine())
        val index = st.nextToken().toInt()
        val high = st.nextToken().toInt()
        highArr[index] = high
        if (highValue < high) {
            highIndex = index
            highValue = high
        }
    }

    var result = 0
    var count = 0
    for (i in 0 until highIndex) {
        count = Math.max(count, highArr[i])
        result += count
    }
    count = 0
    for (i in highArr.size-1 downTo highIndex) {
        count = Math.max(count, highArr[i])
        result += count
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

data class Info(val length: Int, val high: Int)
