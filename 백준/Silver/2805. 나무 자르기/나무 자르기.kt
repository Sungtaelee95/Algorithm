import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toLong()

    st = StringTokenizer(br.readLine())
    val arr = IntArray(N){st.nextToken().toInt()}

    var s = 0L
    var e = Long.MAX_VALUE
    var mid = e

    var result = 0L

    while(s < e){

        mid = (s+e)/2

        var count = 0L

        arr.forEach {
            if(it >= mid){
                count += (it - mid)
            }
        }

        if(count < M){
            e = mid
        }
        if(count >= M){
            result = Math.max(result, mid)
            s = mid + 1
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}