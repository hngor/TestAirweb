package fr.airweb.news.domain.usecase

import fr.airweb.news.data.NewsRepository
import fr.airweb.news.domain.state.GetAllNewsState
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetAllNewsUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(): Single<GetAllNewsState> {
        return Single.create { emitter ->
            newsRepository.getAllNews().subscribeOn(Schedulers.io()).subscribe(
                {
                        success -> emitter.onSuccess(GetAllNewsState.NewsList(success))
                },
                { emitter.onSuccess(GetAllNewsState.NewsListEmpty) }
            )
        }
    }
}