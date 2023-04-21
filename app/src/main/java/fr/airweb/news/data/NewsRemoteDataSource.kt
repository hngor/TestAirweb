package fr.airweb.news.data

import fr.airweb.news.data.model.News
import fr.airweb.news.data.retrofit.NewsService
import io.reactivex.Single


class NewsRemoteDataSource(private val api: NewsService) {

    fun getAllNews(): Single<News> {
        return Single.create { emitter ->
            api.getNews().subscribe(
                { news -> emitter.onSuccess(news) },
                { error -> emitter.onError(error) }
            )
        }
    }
}