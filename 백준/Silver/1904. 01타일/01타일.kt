import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    val N = br.readLine().toInt()

    val arr = LongArray(N+2){0}
    arr[1] = 1
    arr[2] = 2
    for(i in 3 .. N){
        arr[i] = (arr[i-2] + arr[i-1]) % 15746
        //bw.append("${arr[i]} ")
    }
    bw.append("${arr[N] % 15746}")


    bw.flush()
    bw.close()
}