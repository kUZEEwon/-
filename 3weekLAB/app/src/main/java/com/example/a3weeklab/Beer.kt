package com.example.a3weeklab
enum class BeerType{LIGHT_LAGER, LIGHT_HYBRID, AMBER_HYBRID,FRUIT}
enum class WineType{WHITE, ROSE, RED, SPARKLING, DESSERT}
class Beer (var name:String, var beertype:BeerType, var cost:Int)
class Wine (var name:String, var winetype:WineType, var cost:Int)

fun Beer.print(){
    println("맥주이름 : ${name}, 맥주타입 : ${beertype}, 맥주가격 : ${cost}")
}
fun Beer.change_price(newcost: Int){
    this.cost = newcost
}
fun Wine.euro_to_won(){
    val newcost = this.cost * 1350
    println("유로 : ${cost}, 원화 : ${newcost}")
}
fun main(){
    var beer1 = Beer("Hite", BeerType.FRUIT,200)
    var beer2 = Beer("Cass",BeerType.LIGHT_HYBRID,200 )

    beer1.change_price(600)
    beer1.print()
    beer2.change_price(600)
    beer2.print()

    var wine1 = Wine("Cabernet", WineType.RED,10)
    var wine2 = Wine("Chardonnay", WineType.WHITE,12)

    wine1.euro_to_won()
}