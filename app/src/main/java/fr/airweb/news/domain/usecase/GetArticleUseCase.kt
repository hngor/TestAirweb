package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.GetArticleState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetArticleUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(id: Int): Single<GetArticleState> {
        return Single.create { emitter ->
            newsRepository.getArticleFromId(id = id).subscribeOn(Schedulers.io()).subscribe(
                { article ->
                    emitter.onSuccess(GetArticleState.Article(article))
                },
                { emitter.onSuccess(GetArticleState.NoArticle) }
            )
        }
    }
}