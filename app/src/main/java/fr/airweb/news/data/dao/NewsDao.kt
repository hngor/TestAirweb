package fr.airweb.news.data.dao

import androidx.room.Dao
import androidx.room.Query
import fr.airweb.news.data.model.NewsEntity
import fr.airweb.news.util.Constants

@Dao
interface NewsDao {

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME}")
    fun getAllNews(): List<NewsEntity>

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME} WHERE type = :newsType")
    fun getNewsFromType(newsType: String): List<NewsEntity>

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME} ORDER BY date DESC")
    fun getNewsByDate(): List<NewsEntity>

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME} ORDER BY title")
    fun getNewsByTitle(): List<NewsEntity>

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME} WHERE title LIKE :searchValue")
    fun searchNews(searchValue: String): List<NewsEntity>

    @Query("DELETE FROM ${Constants.NEWS_TABLE_NAME}")
        fun clearTable()
}