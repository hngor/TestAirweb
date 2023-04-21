package fr.airweb.news.data.retrofit

import fr.airweb.news.data.model.News
import io.reactivex.Single
import retrofit2.http.GET

interface NewsService {

    @GET("psg.json")
    fun getNews(): Single<News>
}