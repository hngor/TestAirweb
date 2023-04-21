package fr.airweb.news.ui.detail

import android.util.Log
import fr.airweb.news.domain.usecase.GetArticleUseCase
import fr.airweb.news.ui.detail.mapper.DetailPageUiMapper
import fr.airweb.news.ui.detail.model.DetailUiModel
import fr.airweb.news.util.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class DetailPageViewModel(
    private val getArticleUseCase: GetArticleUseCase,
    private val detailPageUiMapper: DetailPageUiMapper
) : BaseViewModel<DetailUiModel>() {

    fun getArticle(id: Int) {
        postData(DetailUiModel.Loading)
        getArticleUseCase(id = id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { state ->
                    postData(detailPageUiMapper.toUiMapper(state))
                },
                { error -> Log.d("TestAirweb", "error : ${error.stackTraceToString()}") }
            )
    }

}