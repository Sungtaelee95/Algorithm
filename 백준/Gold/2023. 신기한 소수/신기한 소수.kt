import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.sqrt

var N = 0
var bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    //val s = System.currentTimeMillis()
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    dfs(2,1)
    dfs(3,1)
    dfs(5,1)
    dfs(7,1)

    bw.flush()
    bw.close()
}

fun dfs(num: Int, len:Int){
    if(len == N) {
        if(!check(num)) return
        bw.appendLine("$num")
    }
    for (i in 1..9) {
        if(check(num)){
            dfs((num*10) + i, len + 1)
        }
    }

}

fun check(num: Int):Boolean{
    if(num == 2) return true
    for(i in 2 .. sqrt(num.toDouble()).toInt()+1){
        if(num%i == 0){
            return false
        }
    }
    return true
}


