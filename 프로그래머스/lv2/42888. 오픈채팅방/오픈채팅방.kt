class Solution {
    fun solution(record: Array<String>): Array<String> {
        var answer = arrayOf<String>()
        val stat = mapOf<String,String>("Enter" to "님이 들어왔습니다.", "Leave" to "님이 나갔습니다.")
        var nameNick = mutableMapOf<String,String>()
        for(temp in record){
            if(temp.split(" ")[0] == "Leave") continue
            else nameNick.put(temp.split(" ")[1], temp.split(" ")[2])
        }

        for(temp in record){
            if(temp.split(" ")[0] == "Enter"){
                answer += nameNick.get(temp.split(" ")[1]).toString() + stat.get("Enter").toString()
            }
            if(temp.split(" ")[0] == "Leave"){
                answer += nameNick.get(temp.split(" ")[1]).toString() + stat.get("Leave").toString()
            }
        }

        return answer
    }
}
