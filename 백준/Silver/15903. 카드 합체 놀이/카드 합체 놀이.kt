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
    val M = st.nextToken().toInt()

    val arr = mutableListOf<Long>()

    st = StringTokenizer(br.readLine())
    repeat(N){
        arr.add(st.nextToken().toLong())
    }

    repeat(M){
        val min = arr.min()
        val minIdx = arr.indexOf(arr.min())
        var secMin = Long.MAX_VALUE
        for(i in 0 .. arr.size-1){
            if (i != minIdx){
                secMin = Math.min(secMin, arr[i])
            }
        }
        arr[minIdx] += secMin
        arr[arr.indexOf(secMin)] += min
    }

    bw.append("${arr.sum()}")

    bw.flush()
    bw.close()
}