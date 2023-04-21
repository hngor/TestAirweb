package fr.airweb.news.domain.state

import fr.airweb.news.data.model.NewsEntity

sealed class GetAllNewsState {
    data class NewsList(val newsList: List<NewsEntity>) : GetAllNewsState()
    object NewsListEmpty : GetAllNewsState()
}
