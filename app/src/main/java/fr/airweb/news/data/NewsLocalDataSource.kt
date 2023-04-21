package fr.airweb.news.data

import fr.airweb.news.data.dao.TestAirwebDB
import fr.airweb.news.data.model.ArticleEntity

class NewsLocalDataSource(private val database: TestAirwebDB) {

    fun addArticle(newsItem: ArticleEntity) {
        database.newsDao().addArticle(newsItem)
    }

    fun getAllArticles(): List<ArticleEntity> {
        return database.newsDao().getAllArticles()
    }

    fun getArticlesFromId(id: Int): ArticleEntity {
        return database.newsDao().getArticleFromId(id = id)
    }

    fun getArticlesByType(newsType: String): List<ArticleEntity> {
        return database.newsDao().getArticlesFromType(articleType = newsType)
    }

    fun searchArticles(searchValue: String): List<ArticleEntity> {
        return database.newsDao().searchArticles(searchValue = searchValue)
    }

    fun clearList() {
        database.newsDao().clearTable()
    }
}