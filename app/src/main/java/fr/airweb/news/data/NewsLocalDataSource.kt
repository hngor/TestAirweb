package fr.airweb.news.data

import fr.airweb.news.data.dao.TestAirwebDB
import fr.airweb.news.data.model.NewsEntity
import fr.airweb.news.util.NewsTypeEnum
import io.reactivex.rxjava3.core.Single

class NewsLocalDataSource(private val database: TestAirwebDB) {
    fun getAllNews(): Single<List<NewsEntity>> {
        return Single.just(database.newsDao().getAllNews())
    }

    fun getNewsByType(newsType: NewsTypeEnum): Single<List<NewsEntity>> {
        return Single.just(database.newsDao().getNewsFromType(newsType = newsType.value))
    }

    fun searchNews(searchValue: String): Single<List<NewsEntity>> {
        return Single.just(database.newsDao().searchNews(searchValue = searchValue))
    }
}