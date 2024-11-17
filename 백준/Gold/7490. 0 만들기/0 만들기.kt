import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val result = mutableSetOf<String>()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = br.readLine().toInt()

    repeat(t) {
        val numbers = IntArray(br.readLine().toInt()) { it + 1 }
        dfs(numbers, 0, numbers.size * 2 - 1, "")
        result.sorted().forEach {
            bw.appendLine(it)
        }
        bw.appendLine()
        result.clear()
    }

    bw.flush()
    bw.close()
}

fun dfs(numbers: IntArray, deep: Int, targetDeep: Int, str: String) {
    if (deep == targetDeep) {
        calculate(str)
        return
    }
    if (deep % 2 == 0) {
        val newStr = str + "${numbers[deep / 2]}"
        dfs(numbers, deep+1, targetDeep, newStr)
    } else {
        dfs(numbers,deep+1, targetDeep, "$str+")
        dfs(numbers,deep+1, targetDeep, "$str-")
        dfs(numbers,deep+1, targetDeep, "$str ")
    }
}

fun calculate(str: String) {
    val trimStr = str.replace(" ", "")
    val nums = trimStr.split("+", "-").map { it.toInt() }
    val scopes = trimStr.replace(Regex("\\d"), "")
    var sum = nums[0]
    for (i in 1 until nums.size) {
        when(scopes[i-1]) {
            '+' -> {
                sum += nums[i]
            }
            '-' -> {
                sum -= nums[i]
            }
        }
    }
    if (sum == 0) result.add(str)
}