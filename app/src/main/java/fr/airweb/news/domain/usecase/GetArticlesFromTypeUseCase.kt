package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.GetArticlesFromTypeState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetArticlesFromTypeUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(type: String): Single<GetArticlesFromTypeState> {
        return Single.create { emitter ->
            newsRepository.getArticlesFromType(type).subscribeOn(Schedulers.io()).subscribe(
                {
                        success -> emitter.onSuccess(GetArticlesFromTypeState.ArticlesList(success))
                },
                { emitter.onSuccess(GetArticlesFromTypeState.ArticlesListEmpty) }
            )
        }
    }
}