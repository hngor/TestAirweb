package fr.airweb.news.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.airweb.news.data.model.ArticleEntity
import fr.airweb.news.util.Constants

@Dao
interface NewsDao {

    @Insert
    fun addArticle(article: ArticleEntity)

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME}")
    fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME} WHERE id = :id")
    fun getArticleFromId(id: Int): ArticleEntity

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME} WHERE type = :articleType")
    fun getArticlesFromType(articleType: String): List<ArticleEntity>

    @Query("SELECT * FROM ${Constants.NEWS_TABLE_NAME} WHERE title LIKE '%' || :searchValue || '%'")
    fun searchArticles(searchValue: String): List<ArticleEntity>

    @Query("DELETE FROM ${Constants.NEWS_TABLE_NAME}")
        fun clearTable()
}