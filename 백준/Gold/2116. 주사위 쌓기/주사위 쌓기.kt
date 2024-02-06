import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()

    val cubes = mutableListOf<Cube>()

    repeat(N) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val d = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        val f = st.nextToken().toInt()
        cubes.add(Cube(listOf(a, f), listOf(b, d), listOf(c, e)))
    }
    var result = 0
    for (i in 1..6) {
        var bottomNumber = i
        var sum = 0
        for (cube in cubes) {
            sum += cube.getSideMaxNumber(bottomNumber)
            bottomNumber = cube.getBottomNumber(bottomNumber)
        }
        result = Math.max(result, sum)
    }
    bw.append("$result")

    bw.flush()
    bw.close()
}

class Cube(private val pair1: List<Int>, private val pair2: List<Int>, private val pair3:List<Int>) {
    fun getSideMaxNumber(bottomNumber: Int): Int {
        var maxNumber = 0
        if (!pair1.contains(bottomNumber)) {
            maxNumber = Math.max(maxNumber,pair1.max())
        }
        if (!pair2.contains(bottomNumber)) {
            maxNumber = Math.max(maxNumber,pair2.max())
        }
        if (!pair3.contains(bottomNumber)) {
            maxNumber = Math.max(maxNumber,pair3.max())
        }
        return maxNumber
    }

    fun getBottomNumber(number: Int): Int {
        if (pair1.contains(number)) {
            return pair1.first { it != number }
        }
        if (pair2.contains(number)) {
            return pair2.first { it != number }
        }
        return pair3.first { it != number }
    }
}
