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
    var dq1 = ArrayDeque<Int>() //오큰수 저장
    var dq2 = ArrayDeque<Int>() // 정답
    var arr1 = IntArray(N){0} // 자료 저장
    var arr2 = IntArray(N){0}
    st = StringTokenizer(br.readLine())

    repeat(N){
        arr1[it] = st.nextToken().toInt()
    }

    for(i in arr1.size-1 downTo 0){
        while(!dq1.isEmpty()){
            if(dq1.peekFirst() > arr1[i]){
                dq2.addFirst(dq1.peekFirst())
                dq1.addFirst(arr1[i])
                break
            } else {
                dq1.removeFirst()
            }
        }
        if(dq1.isEmpty()){
            dq1.addFirst(arr1[i])
            dq2.addFirst(-1)
        }
    }

    while(!dq2.isEmpty()){
        bw.append("${dq2.pollFirst()} ")
    }

    bw.flush()
    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}


