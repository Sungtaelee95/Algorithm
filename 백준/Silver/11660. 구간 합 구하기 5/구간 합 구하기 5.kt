import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = Array(N+1){IntArray(N+1){0}}


    for(i in 1 .. N){
        st = StringTokenizer(readln())
        for(j in 1 .. N){
            arr[i][j] = arr[i][j-1]+ arr[i-1][j] - arr[i-1][j-1] + st.nextToken().toInt()
        }
    }
    var sb = StringBuilder()
    repeat(M){
        st = StringTokenizer(readln())
        var x1 = st.nextToken().toInt()
        var y1 = st.nextToken().toInt()
        var x2 = st.nextToken().toInt()
        var y2 = st.nextToken().toInt()

        sb.appendLine(arr[x2][y2] -arr[x1-1][y2] - arr[x2][y1-1] + arr[x1-1][y1-1])

    }

    println(sb)

}