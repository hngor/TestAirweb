package fr.airweb.news.data.model

import com.squareup.moshi.Json

data class Article(
    @Json(name = "nid") val id: Int,
    val type: String,
    val date: String,
    val title: String,
    val picture: String,
    val content: String,
    @Json(name = "dateformated") val dateFormatted: String
)
