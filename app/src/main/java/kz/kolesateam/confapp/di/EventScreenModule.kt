package kz.kolesateam.confapp.di

import kz.kolesateam.confapp.all_events.data.datasource.AllEventsDataSource
import kz.kolesateam.confapp.all_events.domain.AllEventsRepository
import kz.kolesateam.confapp.all_events.presentation.AllEventsViewModel
import kz.kolesateam.confapp.upcoming_events.data.datasource.UpcomingEventsDataSource
import kz.kolesateam.confapp.upcoming_events.domain.UpcomingEventsRepository
import kz.kolesateam.confapp.upcoming_events.presentation.UpcomingEventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

private const val API_BASE_URL: String = "http://37.143.8.68:2020"

val eventScreenModule: Module = module {

    viewModel {
        UpcomingEventsViewModel(
            upcomingEventsRepository = get(),
            favoriteEventsRepository = get(),
            userDataSource = get(named(SHARED_PREFS_DATA_SOURCE))
        )
    }

    viewModel {
        AllEventsViewModel(
            allEventsRepository = get(),
            favoriteEventsRepository = get()
        )
    }

    single {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    single {
        val retrofit: Retrofit = get()

        retrofit.create(UpcomingEventsDataSource::class.java)
    }

    single {
        val retrofit: Retrofit = get()

        retrofit.create(AllEventsDataSource::class.java)
    }

    factory {
        UpcomingEventsRepository(
            upcomingEventsDataSource = get()
        )
    }

    factory {
        AllEventsRepository(
            allEventsDataSource = get()
        )
    }
}