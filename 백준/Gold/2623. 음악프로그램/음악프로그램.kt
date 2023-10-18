import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val dgree = IntArray(N+1){0}
    val nodeList = Array(N+1){ArrayList<Int>()}

    repeat(M){
        st = StringTokenizer(br.readLine())
        val orderCount = st.nextToken().toInt()
        val tempList = mutableListOf<Int>()
        tempList.add(0)
        repeat(orderCount){
            tempList.add(st.nextToken().toInt())
        }

        for(i in 1 until tempList.size){
            for(j in i+1 until tempList.size){
                nodeList[tempList[i]].add(tempList[j])
                dgree[tempList[j]]++
            }
        }
    }

    val result = ArrayList<Int>()
    val dq = ArrayDeque<Int>()
    for(i in 1 .. dgree.size-1){
        if(dgree[i] == 0){
            dq.addLast(i)
            result.add(i)
        }
    }

    while(!dq.isEmpty()){
        val singer = dq.pollFirst()
        nodeList[singer].forEach {
            dgree[it]--
            if(dgree[it] == 0){
                dq.addLast(it)
                result.add(it)
            }
        }
    }
    if(result.size == N){
        result.forEach {
            bw.appendLine("$it")
        }
        bw.flush()
        bw.close()
        exitProcess(0)
    }
    bw.append("0")
    bw.flush()
    bw.close()
}

