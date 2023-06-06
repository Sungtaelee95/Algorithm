import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    var N = br.readLine().toInt()

    if(N == 0){
        bw.appendLine("0")
        bw.appendLine("0")
    } else if( N > 0){

        bw.appendLine("1")
        val arr = IntArray(N+1){0}
        arr[1] = 1

        for(i in 2 .. arr.size-1){
            arr[i] = ( arr[i-1] + arr[i-2] ) % 1_000_000_000
        }

        bw.appendLine("${arr[N]}")

    } else {
        N = abs(N)
        val arr = IntArray(N+1){0}
        arr[1] = 1
        for(i in 2 .. arr.size-1){
            arr[i] = ( arr[i-2] - arr[i-1] ) % 1_000_000_000
        }
        if(arr[N] > 0){
            bw.appendLine("1")
        } else {
            bw.appendLine("-1")
        }
        bw.appendLine("${abs(arr[N])}")
    }


    bw.flush()
    bw.close()
}