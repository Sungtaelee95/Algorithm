import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val d = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val c = st.nextToken().toInt()

    val foodCheck = IntArray(d + 1) { 0 }
    var foodList = mutableListOf<Int>()
    repeat(N) {
        foodList.add(br.readLine().toInt())
    }
    foodList += foodList

    var start = 0
    var end = k - 1
    var result = 0
    while (end < (2*N) - 1) {
        for (i in start .. end) {
            foodCheck[foodList[i]]++
        }
        if (foodCheck[c] == 0) {
            result = Math.max(result, foodCheck.count { it != 0 } + 1)
        } else {
            result = Math.max(result, foodCheck.count { it != 0 } )
        }
        for (i in start .. end) {
            foodCheck[foodList[i]]--
        }
        start++
        end++
    }
    bw.append("$result")

    bw.flush()
    bw.close()
}