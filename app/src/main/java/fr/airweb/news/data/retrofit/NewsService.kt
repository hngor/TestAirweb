package fr.airweb.news.data.retrofit

import fr.airweb.news.data.model.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("")
    fun getNews(): Response<News>
}