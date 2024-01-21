import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val trees = ArrayDeque<Tree>()// 위치 별 나무의 나이 정보를 가진다.
    val nutritionMap = Array(N + 1) { IntArray(N + 1) { 5 } }
    val s2d2 = Array(N + 1) { intArrayOf() }
    val nextRow = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val nextCol = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

    repeat(N) {
        st = StringTokenizer(br.readLine())
        s2d2[it + 1] = intArrayOf(0) + IntArray(N) { st.nextToken().toInt() }
    }

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        trees.add(Tree(x, y, st.nextToken().toInt(), true))
    }

    repeat(K) {

        val tempDq = ArrayDeque<Tree>()

        while(trees.isNotEmpty()){// 봄
            val tree = trees.removeFirst()
            if (tree.life && tree.age <= nutritionMap[tree.x][tree.y]) {
                nutritionMap[tree.x][tree.y] -= tree.age
                tree.age++
            } else {
                tree.life = false
            }
            tempDq.addLast(tree)
        }

        while(tempDq.isNotEmpty()) {// 여름
            val tree = tempDq.removeFirst()
            if (!tree.life) {
                nutritionMap[tree.x][tree.y] += tree.age / 2
                continue
            }
            trees.addLast(tree)
        }

        while(trees.isNotEmpty()) { // 가을
            val tree = trees.removeFirst()
            if (tree.age % 5 == 0) {
                for (i in 0..7) {
                    val nr = tree.x + nextRow[i]
                    val nc = tree.y + nextCol[i]
                    if (nr > N || nc > N || nr <= 0 || nc <= 0) continue
                    tempDq.addFirst(Tree(nr, nc, 1, true))
                }
            }
            tempDq.addLast(tree)
        }

        for (row in 1..N) {
            for (col in 1..N) {
                nutritionMap[row][col] += s2d2[row][col] // 겨울
            }
        }
        while (tempDq.isNotEmpty()) {
            trees.addLast(tempDq.removeFirst())
        }
    }

    var result = 0
    while(trees.isNotEmpty()) {
        val tree = trees.removeFirst()
        if (tree.life) result++
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

data class Tree(val x: Int, val y: Int, var age: Int, var life: Boolean)

