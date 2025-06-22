import javax.swing.text.View

fun main() {
    val br = System.`in`.bufferedReader()
    val numcnt = br.readLine().toInt()
    val nums = br.readLine().split(" ").map { it.toInt() }
    val inputOpers = br.readLine().split(" ").map { it.toInt() }
    val opers = mutableListOf<Char>()
    inputOpers.forEachIndexed { index, cnt ->
        when (index) {
            0 -> repeat(cnt) {
                opers.add('+')
            }
            1-> repeat(cnt){
                opers.add('-')
            }
            2 -> repeat(cnt){
                opers.add('*')
            }
            3 -> repeat(cnt){
                opers.add('/')
            }
        }
    }
    val (max, min) = Cal(nums, opers).generate()
    print("$max\n$min")
}

class Cal(val nums: List<Int>, val opers: List<Char>) {
    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE
    val visited = BooleanArray(opers.size)
    fun generate(): Pair<Int, Int> {
        for (i in 0 until opers.size) {
            visited[i] = true
            val result = when (opers[i]) {
                '+' -> nums[0] + nums[1]
                '-' -> nums[0] - nums[1]
                '*' -> nums[0] * nums[1]
                '/' -> nums[0] / nums[1]
                else -> 0
            }
            cal(result, 2)
            visited[i] = false
        }
        return Pair(max, min)
    }

    private fun cal(num: Int, deep: Int) {
        if (deep == nums.size) {
            max = maxOf(max, num)
            min = minOf(min, num)
            return
        }
        for (i in 0 until opers.size) {
            if (visited[i]) continue
            visited[i] = true
            val result = when (opers[i]) {
                '+' -> num + nums[deep]
                '-' -> num - nums[deep]
                '*' -> num * nums[deep]
                '/' -> num / nums[deep]
                else -> 0
            }
            cal(result, deep + 1)
            visited[i] = false
        }
    }
}