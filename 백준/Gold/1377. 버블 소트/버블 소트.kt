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
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()

    var arr = Array<info>(N+1){info(0,0)}

    repeat(N){
        arr[it+1] = info(it+1,br.readLine().toInt())
    }
    arr.sortWith(kotlin.Comparator { o1, o2 ->  o1.value.compareTo(o2.value)})

    var max = 0
    for(i in 1 .. arr.size-1){
        max = max(max,arr[i].idx-i)
    }
//    println(arr.toMutableList())
    bw.append((max+1).toString())
    bw.flush()
    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}

