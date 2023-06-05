import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 수열의 개수
    var sb = StringBuilder()

    var stack = Stack<Int>()
    var value = 1
    for(i in 1 .. N){
        st = StringTokenizer(br.readLine())
        var target = st.nextToken().toInt()
        while(stack.isEmpty() || stack.last() < target){
            stack.add(value)
            value++
            sb.append("+\n")
        }

        if(stack.last() == target){
            stack.pop()
            sb.append("-\n")
        }

        if(!stack.isEmpty() && stack.last() > target){
            print("NO")
            return
        }
    }
    print(sb)

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}


