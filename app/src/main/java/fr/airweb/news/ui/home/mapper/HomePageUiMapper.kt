package fr.airweb.news.ui.home.mapper

import fr.airweb.news.domain.state.GetAllArticlesState
import fr.airweb.news.domain.state.GetArticlesFromTypeState
import fr.airweb.news.domain.state.GetSortedArticlesState
import fr.airweb.news.domain.state.SearchArticlesState
import fr.airweb.news.ui.home.model.HomeUiModel
import fr.airweb.news.ui.home.model.NewsUiModel

class HomePageUiMapper {

    fun toUiMapper(state: GetAllArticlesState): HomeUiModel {
        return when (state) {
            is GetAllArticlesState.ArticlesList -> {
                val newsList = mutableListOf<NewsUiModel>()
                state.newsList.forEach { news ->
                    val item = NewsUiModel(id = news.id, title = news.title, picture = news.picture)
                    newsList.add(item)
                }
                HomeUiModel.NewsList(newsList)
            }

            GetAllArticlesState.ArticlesListEmpty -> HomeUiModel.NewsListEmpty
        }
    }

    fun toUiMapper(state: GetArticlesFromTypeState): HomeUiModel {
        return when (state) {
            is GetArticlesFromTypeState.ArticlesList -> {
                val newsList = mutableListOf<NewsUiModel>()
                state.newsList.forEach { news ->
                    val item = NewsUiModel(id = news.id, title = news.title, picture = news.picture)
                    newsList.add(item)
                }
                HomeUiModel.NewsList(newsList)
            }

            GetArticlesFromTypeState.ArticlesListEmpty -> HomeUiModel.NewsListEmpty
        }
    }

    fun toUiMapper(state: GetSortedArticlesState): HomeUiModel {
        return when (state) {
            is GetSortedArticlesState.ArticlesList -> {
                val newsList = mutableListOf<NewsUiModel>()
                state.newsList.forEach { news ->
                    val item = NewsUiModel(id = news.id, title = news.title, picture = news.picture)
                    newsList.add(item)
                }
                HomeUiModel.NewsList(newsList)
            }

            GetSortedArticlesState.ArticlesListEmpty -> HomeUiModel.NewsListEmpty
        }
    }

    fun toUiMapper(state: SearchArticlesState): HomeUiModel {
        return when (state) {
            is SearchArticlesState.ArticlesList -> {
                val newsList = mutableListOf<NewsUiModel>()
                state.newsList.forEach { news ->
                    val item = NewsUiModel(id = news.id, title = news.title, picture = news.picture)
                    newsList.add(item)
                }
                HomeUiModel.NewsList(newsList)
            }

            SearchArticlesState.ArticlesListEmpty -> HomeUiModel.NewsListEmpty
        }
    }
}