package fr.airweb.news.data

import fr.airweb.news.data.model.ArticleEntity
import fr.airweb.news.util.DateConverter
import fr.airweb.news.util.NewsTypeEnum
import io.reactivex.Single

class NewsRepository(
    private val localDataSource: NewsLocalDataSource,
    private val remoteDataSource: NewsRemoteDataSource
) {

    fun getAllArticles(): Single<List<ArticleEntity>> {
        return Single.create { emitter ->
            remoteDataSource.getAllNews().subscribe(
                { success ->
                    val newsList: MutableList<ArticleEntity> = mutableListOf()
                    localDataSource.clearList()
                    success.news.forEach { newsItem ->
                        val articleEntity = ArticleEntity(
                            id = newsItem.id,
                            type = newsItem.type,
                            date = DateConverter.dateToTimestamp(newsItem.date),
                            title = newsItem.title,
                            picture = newsItem.picture,
                            content = newsItem.content,
                            dateFormatted = newsItem.dateFormatted
                        )

                        localDataSource.addArticle(newsItem = articleEntity)
                        newsList.add(articleEntity)
                    }
                    emitter.onSuccess(newsList.filter { article -> article.type == NewsTypeEnum.NEWS.value }
                        .sortedByDescending { article -> article.date })
                },
                {
                    val newsList = localDataSource.getAllArticles()
                    emitter.onSuccess(
                        newsList.filter { article -> article.type == NewsTypeEnum.NEWS.value }
                            .sortedByDescending { article -> article.date })
                }
            )
        }
    }

    fun getArticlesFromType(type: String): Single<List<ArticleEntity>> {
        return Single.create { emitter ->
            val newsList = localDataSource.getArticlesByType(newsType = type)

            emitter.onSuccess(newsList)
        }
    }

    fun getArticleFromId(id: Int): Single<ArticleEntity> {
        return Single.create { emitter ->
            val article = localDataSource.getArticlesFromId(id = id)

            emitter.onSuccess(article)
        }
    }

    fun searchArticles(searchValue: String): Single<List<ArticleEntity>> {
        return Single.create { emitter ->
            val newsList = localDataSource.searchArticles(searchValue = searchValue)

            emitter.onSuccess(newsList)
        }
    }
}