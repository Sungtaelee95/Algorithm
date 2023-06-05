import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()

    var arr = IntArray(10001){0}
    repeat(N){
        st = StringTokenizer(br.readLine())
        arr[st.nextToken().toInt()]++
    }

    for(i in 0 .. arr.size-1){
        repeat(arr[i]){
            bw.appendLine(i.toString())
        }
    }

    bw.flush()
    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}


