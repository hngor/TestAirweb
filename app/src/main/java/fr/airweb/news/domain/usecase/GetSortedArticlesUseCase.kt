package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.GetSortedNewsState
import fr.airweb.news.util.SortNewsEnum
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetSortedNewsUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(type: String, order: String): Single<GetSortedNewsState> {
        return Single.create { emitter ->
            newsRepository.getNewsFromType(type).subscribeOn(Schedulers.io()).subscribe(
                { newsList ->
                    val sortedList = when (order) {
                        SortNewsEnum.TITLE.value -> newsList.sortedBy { article -> article.title }
                        else -> newsList.sortedByDescending { article -> article.date }
                    }

                    emitter.onSuccess(GetSortedNewsState.NewsList(sortedList))

                },
                { emitter.onSuccess(GetSortedNewsState.NewsListEmpty) }
            )
        }
    }
}