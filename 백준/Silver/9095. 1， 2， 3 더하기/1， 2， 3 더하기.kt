import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.sqrt


val bw = BufferedWriter(OutputStreamWriter(System.out))
var target = 0
var count = 0
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()

    repeat(N){
        count = 0
        target = br.readLine().toInt()
        for(i in 1 .. 3){
            dfs(i)
        }
        bw.appendLine("$count")
    }

    bw.flush()
    bw.close()
}

fun dfs(num: Int){
    if(num == target){
        count++
        return
    }
    for(i in 1 .. 3){
        if(num+i <= target) dfs(num+i)
    }
}



