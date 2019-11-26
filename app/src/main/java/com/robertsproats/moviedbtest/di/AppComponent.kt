package com.robertsproats.moviedbtest.di

import com.robertsproats.moviedbtest.MainActivity
import com.robertsproats.movies.di.MoviesModule
import com.robertsproats.repository.di.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    PresentationModule::class,
    DomainModule::class,
    RepositoryModule::class,
    MoviesModule::class
])
@Singleton
interface AppComponent {

    fun inject(application: MovieDBTestApplication)

    fun inject(mainActivity: MainActivity)

}