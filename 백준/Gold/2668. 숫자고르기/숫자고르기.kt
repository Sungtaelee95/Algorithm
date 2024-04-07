
lateinit var map: Array<MutableSet<Int>>
val result = mutableSetOf<Int>()
var start = 0
var N = 0
fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    N = br.readLine().toInt()
    map = Array(N+1) { mutableSetOf<Int>() }


    repeat(N) {
        val start = it + 1
        val end = br.readLine().toInt()
        map[start].add(end)
    }

    for (i in 1 .. N) {
        start = i
        map[i].forEach {
            dfs(it, BooleanArray(N+1) { false })
        }
    }

    bw.appendLine("${result.size}")
    result.sorted().forEach {
        bw.appendLine("$it")
    }

    bw.flush()
    bw.close()
}

fun dfs(index: Int, visit: BooleanArray) {
    visit[index] = true
    if (index == start) {
        for (i in 1 until visit.size) {
            if (visit[i]) {
                result.add(i)
            }
        }
    }
    map[index].forEach {
        if (!visit[it]) {
            dfs(it, visit)
        }
    }
}