package fr.airweb.news.ui.home

import fr.airweb.news.domain.usecase.GetAllNewsUseCase
import fr.airweb.news.ui.home.mapper.HomeUiMapper
import fr.airweb.news.ui.home.model.HomeUiModel
import fr.airweb.news.util.BaseViewModel
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val homeUiMapper: HomeUiMapper
) : BaseViewModel<HomeUiModel>() {

    fun getNews() {
        postData(HomeUiModel.Loading)
        getAllNewsUseCase().subscribeOn(Schedulers.io()).subscribe { state ->
            postData(homeUiMapper.toUiMapper(state))
        }
    }
}