package com.costular.marvelheroes.di.components

import android.content.Context
import android.net.ConnectivityManager
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.di.modules.ApplicationModule
import com.costular.marvelheroes.di.modules.DataModule
import com.costular.marvelheroes.di.modules.NetModule
import com.costular.marvelheroes.presentation.util.Navigator
import com.costular.marvelheroes.presentation.util.SettingsManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, DataModule::class])
interface ApplicationComponent {

    fun getContext(): Context
    fun getRepository(): MarvelHeroesRepositoryImpl
    fun getHeroService(): MarvelHeroesService
    fun getNavigator(): Navigator
    fun getSettingsManager(): SettingsManager
    fun getConnectivityManager(): ConnectivityManager
}