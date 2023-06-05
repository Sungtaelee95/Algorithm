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
    st = StringTokenizer(br.readLine())
    val K = st.nextToken().toInt()

    var start = 1
    var end = K
    var answer = 0
    while(start <= end){
        var count = 0
        var mid = (start+end)/2

        for(i in 1 .. N){
            count += Math.min(mid/i,N)
        }

        if(count < K){
            start = mid +1
        }
        if(count >= K){
            end = mid-1
        }
    }

    println(start)

    //bw.flush()
    //bw.close()
}