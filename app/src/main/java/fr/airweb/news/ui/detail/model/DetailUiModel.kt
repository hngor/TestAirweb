package fr.airweb.news.ui.detail.model

sealed class DetailUiModel {
    data class DetailArticle(val title: String,
    val picture: String,
    val content: String) : DetailUiModel()

    object Error : DetailUiModel()
    object Loading : DetailUiModel()
}