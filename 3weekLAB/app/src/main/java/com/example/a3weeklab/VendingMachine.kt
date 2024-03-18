package com.example.a3weeklab
import java.util.Scanner
import kotlin.math.cos

class VendingMachine (var num: Int,var menu:String, var cost:Int) {
    fun print(){
        println("| (${num}) ${menu}           -   ${cost}원         |")
    }
    fun isNumber(num:String):Boolean{
        if(!isNumeric(num)) return true
        else return false
    }
    fun getMenu():Int{
        // 메뉴 입력 받고 반환
        val scanner = Scanner(System.`in`)
        println("Choose menu:")
        var selcectmenu:String= scanner.nextLine()
        menu = selcectmenu // 메뉴 지정

        while( selcectmenu.isEmpty()|| isNumber(selcectmenu)) {
            println("아무것도 선택하지 않았습니다.")
            println("다시 선택해주세요.")
            selcectmenu = scanner.nextLine()
            menu = selcectmenu // 메뉴 지정
        }
        if(selcectmenu == "1"){
            println("참깨라면이 선택되었습니다.")
            return 1
        }else if(selcectmenu == "2"){
            println("햄버거가 선택되었습니다.")
            return 2
        }else if(selcectmenu == "3"){
            println("콜라가 선택되었습니다.")
            return 3
        }
        else if(selcectmenu == "4"){
            println("핫바가 선택되었습니다.")
            return 4
        }
        else if(selcectmenu == "5"){
            println("초코우유가 선택되었습니다.")
            return 5
        }
        return -1

    }
    fun getPrice(num: Int):Int{ // 선택한 메뉴의 가격정보 가져오기
        if(num ==1) {
            return 1000
        }
        else if( num ==2) {
            return 1500
        }
        else if( num == 3) {
            return 800
        }
        else if( num ==4) {
            return 1200
        }
        else if( num == 5) {
            return 1500
        }else return -9

    }
    fun getCoin():Int{ // 돈 넣기
        val scanner = Scanner(System.`in`)
        println("Insert coin")
        var insert_coin:String =scanner.nextLine()
        if( isNumeric(insert_coin)) cost = insert_coin.toInt()
        while(insert_coin.isEmpty()|| isNumber(insert_coin)){
            println("돈을 넣지 않았습니다.")
            println("다시 돈을 넣어주세요.")
            println("Insert coin")
            insert_coin =scanner.nextLine()
            cost = insert_coin.toInt()
        }
        println("${insert_coin} 원을 넣었습니다.")
        return cost
       // getChange(insert_coin.toInt(), menu.toInt())
    }
    fun getChange(cost:Int, menu:Int):Int{
        // 잔돈 반환
        val ans=cost-getPrice(menu)
        if(ans >= 0){
            println("감사합니다. 잔돈반환:${ans}")
        }else if(ans<0){
            println("현금이 부족합니다.")
        }
        return ans
    }
    fun isNumeric(input: String): Boolean {
        return input.toLongOrNull() != null
    }
}

fun main(){
    var vendingmachine1 = VendingMachine(1,"참깨라면",1000)
    var vendingmachine2 = VendingMachine(2,"햄버거",1500)
    var vendingmachine3 = VendingMachine(3,"콜라",800)
    var vendingmachine4 = VendingMachine(4,"핫바",1200)
    var vendingmachine5 = VendingMachine(5,"초코우유",1500)
    var select = VendingMachine(0,"-9",0)
    while(true){
        println("=========================MENU========================")
        vendingmachine1.print()
        vendingmachine2.print()
        vendingmachine3.print()
        vendingmachine4.print()
        vendingmachine5.print()
        var selectMenu = select.getMenu()
        val insertCost=select.getCoin()
        var check=select.getChange(insertCost, selectMenu)
        if(check >=0) break
        else break
    }
}