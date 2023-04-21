package fr.airweb.news.data

import fr.airweb.news.data.model.News
import fr.airweb.news.data.retrofit.NewsService
import io.reactivex.rxjava3.core.Single

class NewsRemoteDataSource(private val api: NewsService) {

    fun getAllNews(): Single<News> {
        return try {
            val response = api.getNews()
            Single.just(response.body() ?: News(listOf()))
        } catch (exception: Exception) {
            Single.error(exception)
        }
    }
}