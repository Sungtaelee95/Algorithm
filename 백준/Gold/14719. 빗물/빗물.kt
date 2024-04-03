import java.util.*

// 아이디어 가로세로 뒤집어서 생각
// 벽 을 만난뒤 다음 벽을 만날 때 까지 숫자 카운트
fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val H = st.nextToken().toInt()
    val W = st.nextToken().toInt()

    var result = 0

    val arr = Array(W) { BooleanArray(H) } // true 는 벽을 의미
    st = StringTokenizer(br.readLine())
    repeat(W) { row ->
        repeat(st.nextToken().toInt()) { col ->
            arr[row][col] = true
        }
    }

    for (col in 0 until H) {
        var count = 0
        var isCount = false
        for (row in 0 until W) {
            if (arr[row][col]) {
                result += count
                count = 0
                isCount = true
                continue
            }
            if (isCount) count++
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}
