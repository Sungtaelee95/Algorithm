import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val chars = IntArray(26)
    br.readLine().forEach{ char ->
        chars[char-'A']++
    }
    if (chars['M'-'A'] > 0 && chars['O'-'A'] > 0 && chars['B'-'A'] > 0 && chars['I'-'A'] > 0 && chars['S'-'A'] > 0) {
        bw.append("YES")
    } else {
        bw.append("NO")
    }

    bw.flush()
    bw.close()
}