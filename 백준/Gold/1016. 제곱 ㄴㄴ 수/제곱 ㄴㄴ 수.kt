import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (min, max) = br.readLine().split(" ").map { it.toLong() }

    val prime = BooleanArray(1000001) { true }
    prime[1] = false

    for (i in 2..1000000) {
        if (prime[i]) {
            var j = 2
            while (i * j <= 1000000) {
                prime[i * j] = false
                j++
            }
        }
    }

    val checked = BooleanArray((max - min + 1).toInt()) { false }

    var num = 2L
    while (num * num <= max) {
        if (!prime[num.toInt()]) {
            num++
            continue
        }
        val square = num * num
        var j = min / square + (if (min % square != 0L) 1 else 0)
        while (j * square <= max) {
            if (!checked[(j * square - min).toInt()]) {
                checked[(j * square - min).toInt()] = true
            }
            j++
        }
        num++
    }

    var cnt = 0
    for (i in min..max) {
        if (!checked[(i - min).toInt()]) cnt++
    }

    bw.append("$cnt")

    bw.flush()
    bw.close()
}

