package fr.airweb.news.util

enum class NewsTypeEnum(val value: String) {
    NEWS("news"),
    ACTUALITE("actualité"),
    HOT("hot")
}

enum class SortNewsEnum(val value: String) {
    DATE("date"),
    TITLE("title")
}