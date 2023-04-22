package com.example.expandablelistapp

class CardData {
    fun loadCardList():List<CardItem>{
        return listOf(
            CardItem("내쉬", "안녕하세요"),
            CardItem("필립", "안녕하세요~"),
            CardItem("아리아", "안녕하세요!"),
            CardItem("캘리", "안녕하세여~")
        )
    }
}