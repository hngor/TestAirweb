package fr.airweb.news.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.airweb.news.util.Constants

@Entity(tableName = Constants.NEWS_TABLE_NAME)
data class NewsEntity(
    @PrimaryKey val id: Int,
    val type: String,
    val date: Long,
    val title: String,
    val picture: String,
    val content: String,
    val dateFormatted: String
)