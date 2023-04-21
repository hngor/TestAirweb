package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.GetNewsFromTypeState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetNewsFromTypeUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(type: String): Single<GetNewsFromTypeState> {
        return Single.create { emitter ->
            newsRepository.getNewsFromType(type).subscribeOn(Schedulers.io()).subscribe(
                {
                        success -> emitter.onSuccess(GetNewsFromTypeState.NewsList(success))
                },
                { emitter.onSuccess(GetNewsFromTypeState.NewsListEmpty) }
            )
        }
    }
}