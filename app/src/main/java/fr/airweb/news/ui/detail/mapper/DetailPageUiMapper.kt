package fr.airweb.news.ui.detail.mapper

import fr.airweb.news.domain.state.GetArticleState
import fr.airweb.news.ui.detail.model.DetailUiModel

class DetailPageUiMapper {

    fun toUiMapper(state: GetArticleState): DetailUiModel {
        return when (state) {
            is GetArticleState.Article -> {
                DetailUiModel.DetailArticle(
                    title = state.article.title,
                    picture = state.article.picture,
                    content = state.article.content
                )
            }

            GetArticleState.NoArticle -> DetailUiModel.Error
        }
    }
}