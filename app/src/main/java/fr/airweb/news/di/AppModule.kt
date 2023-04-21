package fr.airweb.news.di

import androidx.room.Room
import fr.airweb.news.data.NewsLocalDataSource
import fr.airweb.news.data.NewsRemoteDataSource
import fr.airweb.news.data.NewsRepository
import fr.airweb.news.data.dao.TestAirwebDB
import fr.airweb.news.data.retrofit.NewsService
import fr.airweb.news.data.retrofit.RetrofitClient
import fr.airweb.news.domain.usecase.GetAllArticlesUseCase
import fr.airweb.news.domain.usecase.GetArticleUseCase
import fr.airweb.news.domain.usecase.GetArticlesFromTypeUseCase
import fr.airweb.news.domain.usecase.GetSortedArticlesUseCase
import fr.airweb.news.domain.usecase.SearchArticlesUseCase
import fr.airweb.news.ui.detail.DetailPageViewModel
import fr.airweb.news.ui.detail.mapper.DetailPageUiMapper
import fr.airweb.news.ui.home.HomePageViewModel
import fr.airweb.news.ui.home.mapper.HomePageUiMapper
import fr.airweb.news.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NewsLocalDataSource(database = get()) }
    single { NewsRemoteDataSource(api = get()) }
    single { NewsRepository(localDataSource = get(), remoteDataSource = get()) }

    single { GetAllArticlesUseCase(newsRepository = get()) }
    single { GetArticlesFromTypeUseCase(newsRepository = get()) }
    single { GetSortedArticlesUseCase(newsRepository = get()) }
    single { SearchArticlesUseCase(newsRepository = get()) }
    single { GetArticleUseCase(newsRepository = get()) }

    single { HomePageUiMapper() }
    single { DetailPageUiMapper() }

    viewModel {
        HomePageViewModel(
            getAllArticlesUseCase = get(),
            getArticlesFromTypeUseCase = get(),
            getSortedArticlesUseCase = get(),
            searchArticlesUseCase = get(),
            homePageUiMapper = get()
        )
    }

    viewModel {
        DetailPageViewModel(
            getArticleUseCase = get(),
            detailPageUiMapper = get()
        )
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            TestAirwebDB::class.java, Constants.DATABASE_NAME
        )
            .build()
    }

    single {
        val retrofit = RetrofitClient.getInstance()
        retrofit.create(NewsService::class.java)
    }
}