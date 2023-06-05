import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var arr: IntArray
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var n = st.nextToken().toInt()
    var m = st.nextToken().toInt()

    arr = IntArray(n+1){i: Int ->  i}
    repeat(m){
        st = StringTokenizer(br.readLine())
        var operation = st.nextToken().toInt() // 0일 경우 합집합, 1일 경우 포함되어있는지 확인
        var a = st.nextToken().toInt() // 대표 노드
        var b = st.nextToken().toInt() // 자식 노드
        when(operation){
            0 -> union(a,b)
            1 -> check(a,b)
        }
    }

    bw.flush()
    bw.close()
}

fun union(a:Int,b:Int){
    val A = find(a)
    val B = find(b)
    if(A < B) arr[B] = A
    else arr[A] = B
}

fun find(a:Int):Int{
    if(a != arr[a]){
        arr[a] = find(arr[a])
    }
    return arr[a]
}

fun check(a:Int,b:Int){
    if(find(a) == find(b)) bw.appendLine("YES")
    else bw.appendLine("NO")
}

