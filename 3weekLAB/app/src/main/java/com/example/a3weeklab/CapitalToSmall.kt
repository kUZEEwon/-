package com.example.a3weeklab

class CapitalToSmall {
}
fun isSmall_my(str:String): Int{
   // 소문자 인가 확인
    var lowercase=""
    for( char in str){
        val upperChar = if (char in 'a'..'z') {
            'A' + (char - 'a')
        } else {
            return 0
        }
        lowercase += upperChar
    }
    return 1
}

fun change(str: String):String{
    // 에러 처리 ( 소문자만 들어와야 함 )
    // 대문자랑 섞여 들어왔을 경우
    var ans=""
    var check =1

    for (char in str) {
        if (isSmall_my(char.toString())==0) {
            check=0
            ans = ans+ char
        }
    }

    if( ans != null && check==0){
        return "error with = $ans"
    }

    var result = ""
    for (char in str) {
        val upperChar = if (char in 'a'..'z') {
            'A' + (char - 'a')
        } else {
            char
        }
        result += upperChar
    }
    return result
}

fun main(){
    var a="abcd"
    println(change(a))
    var b="EfgH"
    println(change(b))
    var c="!@%$"
    println(change(c))
}