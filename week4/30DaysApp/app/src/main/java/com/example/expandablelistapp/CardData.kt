package com.example.expandablelistapp

class CardData {
    fun loadCardList():List<CardItem>{
        return listOf(
            CardItem("내쉬", "안녕하세요", R.drawable.choonsik_01),
            CardItem("필립", "안녕하세요~",R.drawable.choonsik_02),
            CardItem("아리아", "안녕하세요!",R.drawable.choonsik_03),
            CardItem("캘리", "안녕하세여~",R.drawable.choonsik_01)
        )
    }
}