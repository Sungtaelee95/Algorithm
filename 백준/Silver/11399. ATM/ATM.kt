import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.max
import java.util.*

data class info(val idx: Int, val value: Int)

fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
//    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()

    var arr = IntArray(N){0}
    st = StringTokenizer(br.readLine())
    repeat(N){
        arr[it] = st.nextToken().toInt()
    }

    Arrays.sort(arr)
    var arr2 = IntArray(N){0}
    arr2[0] = arr[0]
    for(i in 1 .. arr.size-1){
        arr2[i] = arr2[i-1]+arr[i]
    }
    print(arr2.sum())

//    bw.flush()
//    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}

