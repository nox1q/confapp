package kz.kolesateam.confapp.di

import com.fasterxml.jackson.databind.ObjectMapper
import kz.kolesateam.confapp.favourite_events.DefaultFavoriteEventsRepository
import kz.kolesateam.confapp.favourite_events.FavoriteEventsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.core.module.Module


val favoritesModule: Module = module {

    single {
        ObjectMapper()
    }

    factory<FavoriteEventsRepository> {
        DefaultFavoriteEventsRepository(
            context = androidContext(),
            objectMapper = get()
        )
    }
}