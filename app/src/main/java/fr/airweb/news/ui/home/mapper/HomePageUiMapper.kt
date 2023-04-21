package fr.airweb.news.ui.home.mapper

import fr.airweb.news.domain.state.GetAllNewsState
import fr.airweb.news.ui.home.model.HomeUiModel
import fr.airweb.news.ui.home.model.NewsUiModel

class HomeUiMapper {

    fun toUiMapper(state: GetAllNewsState): HomeUiModel {
        return when(state) {
            is GetAllNewsState.NewsList -> {
                val newsList = mutableListOf<NewsUiModel>()
                state.newsList.forEach { news ->
                val item = NewsUiModel(id = news.id, title = news.title, picture = news.picture)
                    newsList.add(item)
                }
                HomeUiModel.NewsList(newsList)
            }
            GetAllNewsState.NewsListEmpty -> HomeUiModel.NewsListEmpty
        }
    }
}