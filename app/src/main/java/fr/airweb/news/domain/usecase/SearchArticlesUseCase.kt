package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.SearchArticlesState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SearchArticlesUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(value: String): Single<SearchArticlesState> {
        return Single.create { emitter ->
            newsRepository.searchArticles(value).subscribeOn(Schedulers.io()).subscribe(
                { newsList ->
                    emitter.onSuccess(SearchArticlesState.ArticlesList(newsList))
                },
                { emitter.onSuccess(SearchArticlesState.ArticlesListEmpty) }
            )
        }
    }
}