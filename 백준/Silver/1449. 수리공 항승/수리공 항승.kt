import java.util.*


fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val tapeLen = st.nextToken().toInt()
    val pipe = IntArray(N)

    st = StringTokenizer(br.readLine())
    for (i in 0 until N) {
        pipe[i] = st.nextToken().toInt()
    }
    Arrays.sort(pipe)
    var range = (pipe[0] - 0.5 + tapeLen).toInt() //최소 0.5이므로 0.5전부터 테이브 길이 이 값을 다음 값이 벗어난다면 하나 붙이는 식으로
 
    var count = 1 //가장 첫번째는 담았기에 카운트

    //다음거랑 L범위 안에 있다면 카운트 1 아니면 2
    for (i in 1 until pipe.size) {
        if (range < (pipe[i] + 0.5).toInt()) {
            range = (pipe[i] - 0.5 + tapeLen).toInt()
            count++
        }
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}

