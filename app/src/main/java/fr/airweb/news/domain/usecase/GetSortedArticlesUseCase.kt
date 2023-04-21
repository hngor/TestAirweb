package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.GetSortedArticlesState
import fr.airweb.news.util.SortNewsEnum
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetSortedArticlesUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(type: String, order: String): Single<GetSortedArticlesState> {
        return Single.create { emitter ->
            newsRepository.getArticlesFromType(type).subscribeOn(Schedulers.io()).subscribe(
                { newsList ->
                    val sortedList = when (order) {
                        SortNewsEnum.TITLE.value -> newsList.sortedBy { article -> article.title }
                        else -> newsList.sortedByDescending { article -> article.date }
                    }

                    emitter.onSuccess(GetSortedArticlesState.ArticlesList(sortedList))

                },
                { emitter.onSuccess(GetSortedArticlesState.ArticlesListEmpty) }
            )
        }
    }
}