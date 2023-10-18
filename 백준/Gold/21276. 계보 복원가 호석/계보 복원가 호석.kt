import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()

    val parentByChild = HashMap<String, MutableList<String>>() // 자식 후보 리스트
    val parentByDegree = HashMap<String, Int>()

    val parentByChildReverse = HashMap<String, MutableList<String>>()
    val parentByDegreeReverse = HashMap<String, Int>()

    st = StringTokenizer(br.readLine())
    repeat(N){
        val human = st.nextToken()
        parentByChild[human] = mutableListOf()
        parentByDegree[human] = 0

        parentByChildReverse[human] = mutableListOf()
        parentByDegreeReverse[human] = 0
    }

    st = StringTokenizer(br.readLine())
    val M = st.nextToken().toInt()
    repeat(M){
        st = StringTokenizer(br.readLine())
        val child = st.nextToken()
        val parent = st.nextToken()
        parentByChild[parent]!!.add(child)
        parentByDegree[child] = parentByDegree[child]!! + 1

        parentByChildReverse[parent]!!.add(child)
        parentByDegreeReverse[child] = parentByDegreeReverse[child]!! + 1

    }

    val resultMap = HashMap<String, MutableSet<String>>()

    var rootCount = 0
    val sb = StringBuilder()
    val dq = ArrayDeque<String>()
    val dq2 = ArrayDeque<String>()

    parentByDegree.keys.sorted().forEach{
        if(parentByDegree[it] == 0){
            sb.append("$it ")
            dq.addLast(it)
            dq2.addFirst(it)
            rootCount++
        }
        resultMap[it] = mutableSetOf()
    }

    bw.appendLine("$rootCount")
    bw.appendLine(sb)

    while (!dq.isEmpty() && !dq2.isEmpty()){ // 정방향
        if(!dq.isEmpty()){
            val parent = dq.pollFirst()
            parentByChild[parent]!!.sorted().forEach {
                parentByDegree[it] = parentByDegree[it]!! - 1
                if(parentByDegree[it]!! <= 0){
                    resultMap[parent]!!.add(it)
                    dq.addLast(it)
                }
            }
        }
        if(!dq2.isEmpty()){
            val parent = dq2.pollFirst()
            parentByChildReverse[parent]!!.sortedDescending().forEach {
                parentByDegreeReverse[it] = parentByDegreeReverse[it]!! - 1
                if(parentByDegreeReverse[it]!! <= 0){
                    resultMap[parent]!!.add(it)
                    dq2.addLast(it)
                }
            }
        }
    }

    resultMap.keys.sorted().forEach {
        bw.append("$it ${resultMap[it]!!.size}")
        resultMap[it]!!.sorted().forEach { child ->
            bw.append(" $child")
        }
        bw.appendLine()
    }

    bw.flush()
    bw.close()

}
