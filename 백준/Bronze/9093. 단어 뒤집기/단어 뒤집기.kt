import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    val n = readln().toInt()

    repeat(n){
        val str = readln()
        val stack = Stack<Char>()
        var result = StringBuilder()

        str.toCharArray().forEach {
            if(it == ' '){
                repeat(stack.size){
                    result.append(stack.pop())
                }
                result.append(" ")
            } else {
                stack.push(it)
            }
        }

        repeat(stack.size){
            result.append(stack.pop())
        }

        bw.appendLine(result.toString())

    }

    bw.flush()
    bw.close()
}