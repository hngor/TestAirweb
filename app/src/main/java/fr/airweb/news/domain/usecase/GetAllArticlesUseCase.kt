package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.GetAllArticlesState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetAllArticlesUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(): Single<GetAllArticlesState> {
        return Single.create { emitter ->
            newsRepository.getAllArticles().subscribeOn(Schedulers.io()).subscribe(
                {
                        success -> emitter.onSuccess(GetAllArticlesState.ArticlesList(success))
                },
                { emitter.onSuccess(GetAllArticlesState.ArticlesListEmpty) }
            )
        }
    }
}