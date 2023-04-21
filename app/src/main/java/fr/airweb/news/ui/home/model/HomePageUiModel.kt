package fr.airweb.news.ui.home.model

sealed class HomeUiModel {
    data class NewsList(val newsList: List<NewsUiModel>) : HomeUiModel()
    object NewsListEmpty : HomeUiModel()
    object Loading : HomeUiModel()
}

data class NewsUiModel(
    val id: Int,
    val title: String,
    val picture: String
)