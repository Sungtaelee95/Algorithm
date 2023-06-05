class Solution {
    fun solution(numbers: IntArray): String {
        var answer = numbers.sortedWith(Comparator { o1, o2 -> "$o2$o1".compareTo("$o1$o2")}).joinToString("")
        if(answer[0] == '0') return "0"
        return answer
    }
}