import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

lateinit var arr: LongArray
var arrlen = 1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()
    var M = st.nextToken().toInt()
    var K = st.nextToken().toInt()


    while (arrlen < N) {
        arrlen *= 2
    }
    arr = LongArray(arrlen * 2) { 0 }

    repeat(N) {
        st = StringTokenizer(br.readLine())
        update(it,st.nextToken().toLong())
    }
    
    repeat(M+K){
        st = StringTokenizer(br.readLine())
        val check = st.nextToken().toInt()
        if( check == 1) update(st.nextToken().toInt()-1,st.nextToken().toLong())
        else bw.appendLine(query(st.nextToken().toInt()-1,st.nextToken().toInt()-1).toString())
    }

    bw.flush()
    bw.close()

}

fun update(idx: Int, num: Long){ // 인덱스 받을 때 +arrlen해서 받을 것
    var nidx = idx+arrlen
    arr[nidx] = num
    nidx /= 2

    while(nidx > 0){
        arr[nidx] = arr[nidx*2] + arr[(nidx*2) +1]
        nidx /= 2
    }

}

fun query(start:Int,end:Int):Long{
    var sum = 0L
    var left = start+arrlen
    var right = end+arrlen

    while(left <= right){
        if(left % 2 == 1){
            sum += arr[left]
        }
        if(right % 2 == 0){
            sum += arr[right]
        }
        left = (left+1)/2
        right = (right-1)/2
    }

    return sum
}