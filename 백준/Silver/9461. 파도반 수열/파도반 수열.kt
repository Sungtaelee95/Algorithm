import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    val N = br.readLine().toInt()

    val caseArray = IntArray(N){0}

    // 현재 케이스 중 가장 높은 수를 구한다.
    repeat(N){
        caseArray[it] =  br.readLine().toInt()
    }

    // 케이스 중 높은 수 만큼의 수열을 구한다.
    val result = LongArray(caseArray.max()+1){0}
    result[1] = 1
    result[2] = 1
    for(i in 3 .. result.size-1){
        result[i] = result[i-2] + result[i-3]
    }

    for(case in caseArray){
        bw.appendLine("${result[case]}")
    }

    bw.flush()
    bw.close()
}