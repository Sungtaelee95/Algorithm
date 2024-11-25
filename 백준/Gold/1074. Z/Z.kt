import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.pow

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val size = 2.0.pow(N).toInt()
    bw.append("${findZIndex(size, r, c, 0, 0, 0)}")
    bw.flush()
    bw.close()
}

fun findZIndex(size: Int, r: Int, c: Int, startR: Int, startC: Int, baseOrder: Int): Int {
    if (size == 1) return baseOrder

    val half = size / 2

    return when {
        r < startR + half && c < startC + half -> {
            findZIndex(half, r, c, startR, startC, baseOrder)
        }

        r < startR + half && c >= startC + half -> {
            findZIndex(half, r, c, startR, startC + half, baseOrder + half * half)
        }

        r >= startR + half && c < startC + half -> {
            findZIndex(half, r, c, startR + half, startC, baseOrder + 2 * half * half)
        }

        else -> {
            findZIndex(half, r, c, startR + half, startC + half, baseOrder + 3 * half * half)
        }
    }
}