package fr.airweb.news.ui.home

import android.util.Log
import fr.airweb.news.domain.usecase.GetAllArticlesUseCase
import fr.airweb.news.domain.usecase.GetArticlesFromTypeUseCase
import fr.airweb.news.domain.usecase.GetSortedArticlesUseCase
import fr.airweb.news.domain.usecase.SearchArticlesUseCase
import fr.airweb.news.ui.home.mapper.HomePageUiMapper
import fr.airweb.news.ui.home.model.HomeUiModel
import fr.airweb.news.util.BaseViewModel
import fr.airweb.news.util.NewsTypeEnum
import fr.airweb.news.util.SortNewsEnum
import io.reactivex.android.schedulers.AndroidSchedulers

class HomePageViewModel(
    private val getAllArticlesUseCase: GetAllArticlesUseCase,
    private val getArticlesFromTypeUseCase: GetArticlesFromTypeUseCase,
    private val getSortedArticlesUseCase: GetSortedArticlesUseCase,
    private val searchArticlesUseCase: SearchArticlesUseCase,
    private val homePageUiMapper: HomePageUiMapper
) : BaseViewModel<HomeUiModel>() {

    var displayNewsType: String = NewsTypeEnum.NEWS.value
    var sortArticles: String = SortNewsEnum.DATE.value

    fun getNews() {
        postData(HomeUiModel.Loading)
        getAllArticlesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { state ->
                    postData(homePageUiMapper.toUiMapper(state))
                },
                { error -> Log.d("TestAirweb", "error : ${error.stackTraceToString()}") }
            )
    }

    fun getNewsFromType() {
        postData(HomeUiModel.Loading)
        getArticlesFromTypeUseCase(displayNewsType)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { state ->
                    postData(homePageUiMapper.toUiMapper(state))
                },
                { error -> Log.d("TestAirweb", "error : ${error.stackTraceToString()}") }
            )
    }

    fun getSortedNews() {
        postData(HomeUiModel.Loading)
        getSortedArticlesUseCase(type = displayNewsType, order = sortArticles)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { state ->
                    postData(homePageUiMapper.toUiMapper(state))
                },
                { error -> Log.d("TestAirweb", "error : ${error.stackTraceToString()}") }
            )
    }

    fun searchNews(value: String) {
        postData(HomeUiModel.Loading)
        if (value.isNotEmpty()) {
            searchArticlesUseCase(value)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { state ->
                        postData(homePageUiMapper.toUiMapper(state))
                    },
                    { error -> Log.d("TestAirweb", "error : ${error.stackTraceToString()}") }
                )
        }
    }
}