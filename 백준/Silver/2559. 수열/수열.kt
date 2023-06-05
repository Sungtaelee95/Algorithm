import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()
    var K = st.nextToken().toInt()

    var arr = IntArray(N){0}

    st = StringTokenizer(br.readLine())
    repeat(N){
        arr[it] = st.nextToken().toInt()
    }
    var sum = 0
    var left = 0
    var right = K
    for(i in left .. right-1){
        sum += arr[i]
    }

    var answer = sum // 초기값 세팅
    
    for(i in K .. N-1){
        sum -= arr[left]
        sum += arr[right]
        left++
        right++
        answer = Math.max(sum,answer)
    }
    
    println(answer)

}

