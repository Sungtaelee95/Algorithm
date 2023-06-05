import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    // 좌석의 개수
    val N = br.readLine().toInt()
    // 고정석의 개수
    val M = br.readLine().toInt()
    val arr = IntArray(41){0}
    arr[1] = 1
    arr[2] = 2
    for(i in 3 .. 40){
        arr[i] = arr[i-2] + arr[i-1]
    }

    var result = 1
    var num = 0

    repeat(M){
        val fix = br.readLine().toInt()
        if(fix-1-num != 0){
            result *= arr[fix-num-1]
        }
        num = fix
    }

    if(N-num != 0){
        result *= arr[N-num]
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}