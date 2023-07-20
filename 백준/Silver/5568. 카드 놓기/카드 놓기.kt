import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger


val bw = BufferedWriter(OutputStreamWriter(System.out))
val visit = BooleanArray(100_000_001)
lateinit var idx : BooleanArray
var count = 0
var K = 0
lateinit var arr : Array<BigInteger>
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()
    K = br.readLine().toInt()
    idx = BooleanArray(N)
    arr = Array(N){br.readLine().toBigInteger()}

    for(i in 0 until arr.size){
        idx[i] = true
        dfs(arr[i], 1)
        idx[i] = false
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}

fun dfs(num: BigInteger, deep: Int){
    if(deep == K){
        if(!visit[num.toInt()]){
            count++
            visit[num.toInt()] = true
        }
        return
    }

    for(i in 0 .. arr.size-1){
        if(!idx[i]){

            val next = StringBuilder()
                .append("$num")
                .append("${arr[i]}")
                .toString().toInt()

            idx[i] = true
            dfs(next.toBigInteger(), deep+1)
            idx[i] = false
        }
    }

}
