class Solution {

    lateinit var visitcount : HashMap<Info,Int> // 해당 지점에 갈 수 있는 횟수
    lateinit var visitList : HashMap<String,ArrayList<String>> // 출발지점에서 갈 수 있는 도시
    var possibly = mutableListOf<Array<String>>()
    var count = 0

    data class Info(
        val start: String,
        val next: String
    )
    
    fun solution(tickets: Array<Array<String>>): Array<String> {
        visitcount = HashMap<Info,Int>() // 해당 지점에 갈 수 있는 횟수
        visitList = HashMap<String,ArrayList<String>>()

        
        for(ticket in tickets){
            if(visitcount.containsKey(Info(ticket[0],ticket[1]))){
                visitcount[Info(ticket[0],ticket[1])] = visitcount[Info(ticket[0],ticket[1])]!!+1
            } else {
                visitcount[Info(ticket[0],ticket[1])] = 1
            }

            if(visitList.containsKey(ticket[0])){
                visitList[ticket[0]]!!.add(ticket[1])
                visitList[ticket[0]]!!.distinct()
            } else {
                var temp = ArrayList<String>()
                temp.add(ticket[1])
                visitList[ticket[0]] = temp
            }
        }

        var temp = Array<String>(2,{""})
        temp[0] = "ICN"
        for(i in 0 .. visitList["ICN"]!!.size-1){
            temp[1] = visitList["ICN"]!![i]
            dfs(Info("ICN",visitList["ICN"]!![i]), temp)
        }

        possibly.sortWith(java.util.Comparator { o1, o2 -> o1.joinToString().compareTo(o2.joinToString()) })
        //println(possibly)

        return possibly.first()
    }

    fun dfs(now: Info,his:Array<String>){

        visitcount[now] = visitcount[now]!!-1

        if(visitcount.values.sum() == 0){
            possibly.add(his)
            visitcount[now] = visitcount[now]!!+1
            return
        }


        var temp = Array<String>(his.size+1,{""})
        if(!visitList[now.next].isNullOrEmpty()){
            for(i in 0 .. his.size-1){
                temp[i] = his[i]
            }
            for(i in 0 .. visitList[now.next]!!.size-1){
                if(visitcount[Info(now.next,visitList[now.next]!![i])]!! > 0){
                    temp[his.size] = visitList[now.next]!![i]
                    dfs(Info(now.next,visitList[now.next]!![i]),temp)
                }
            }
        }
        visitcount[now] = visitcount[now]!!+1
    }

}