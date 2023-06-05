import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    st = StringTokenizer(readln())
    var sumarr = IntArray(N+1){0}

    repeat(N){
        sumarr[it+1] = st.nextToken().toInt() + sumarr[it]
    }

    var sb = StringBuilder()

    repeat(M){
        val st = StringTokenizer(readln())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        sb.appendLine(sumarr[end]-sumarr[start-1])
    }
    println(sb)
}