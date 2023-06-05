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
    val M = st.nextToken().toInt()
    var arr = IntArray(N){0}
    st = StringTokenizer(br.readLine())
    repeat(N){
        arr[it] = st.nextToken().toInt()
    }
    var start = arr.max()
    var end = arr.sum()

    while(start < end){
        var count = 1
        val mid = (start+end)/2
        var sum = 0
        for(num in arr){
            if(sum + num <= mid){
                sum+=num
            } else {
                sum = num
                count++
            }
        }
        if(count <= M){
            end = mid
        }else{
            start = mid+1
        }
    }
    println(start)

    //bw.flush()
    //bw.close()
}