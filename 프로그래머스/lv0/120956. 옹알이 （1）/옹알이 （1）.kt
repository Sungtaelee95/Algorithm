class Solution {
    fun solution(babbling: Array<String>): Int {
        var answer: Int = 0
        val soundList = listOf<String>("aya", "ye", "woo", "ma")
        babbling.forEach{
            var temp = it
            for(i in 0 .. soundList.size-1){
                temp = temp.replace(soundList[i],"0")
            }
            if(temp.replace("0","") == "") answer++
        }
        return answer
    }
}