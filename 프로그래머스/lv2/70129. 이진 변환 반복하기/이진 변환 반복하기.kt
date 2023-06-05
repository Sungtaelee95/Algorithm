class Solution {
    fun solution(s: String): IntArray {
        var count = 0
        var delZero = 0
        var temp = s
        while(temp != "1"){
            var oneCount = 0
            delZero +=temp.count{e-> e == '0'} // 사라질 0들의 갯수
            oneCount += temp.count{e -> e == '1'}
            temp = oneCount.toString(2) // 1의 갯수를 이진법으로 저장
            count++
        }
        
        var answer: IntArray = intArrayOf(count,delZero)
        return answer
    }
}