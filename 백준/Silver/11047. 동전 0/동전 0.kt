import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.max
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    var K = st.nextToken().toInt()
    var arr = IntArray(N){0}
    repeat(N){
        st = StringTokenizer(br.readLine())
        arr[N-it-1] = st.nextToken().toInt()
    }

    var answer = 0

    for(coin in arr){
        if(K >= coin){
            answer += K/coin
            K = K % coin
        }
        if(K == 0)break
    }
    println(answer)



    //bw.flush()
    //bw.close()
}