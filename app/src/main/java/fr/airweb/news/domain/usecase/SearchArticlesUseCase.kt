package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.SearchNewsState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SearchNewsUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(value: String): Single<SearchNewsState> {
        return Single.create { emitter ->
            newsRepository.searchNews(value).subscribeOn(Schedulers.io()).subscribe(
                { newsList ->
                    emitter.onSuccess(SearchNewsState.NewsList(newsList))
                },
                { emitter.onSuccess(SearchNewsState.NewsListEmpty) }
            )
        }
    }
}