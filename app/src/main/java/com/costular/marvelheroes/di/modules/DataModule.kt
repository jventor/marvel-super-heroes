package com.costular.marvelheroes.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.costular.marvelheroes.data.db.HeroDatabase
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelDatasource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.presentation.util.SettingsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroMapper = MarvelHeroMapper()

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSource(marvelHeroesService: MarvelHeroesService, marvelHeroMapper: MarvelHeroMapper)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService, marvelHeroMapper)

    @Provides
    @Singleton
    fun provideLocalMarvelHeroesDataSource(heroDatabase: HeroDatabase) : LocalMarvelDatasource = LocalMarvelDatasource(heroDatabase)

    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(
            marvelRemoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
            localMarvelDataSource: LocalMarvelDatasource,
            settingsManager: SettingsManager): MarvelHeroesRepositoryImpl =
            MarvelHeroesRepositoryImpl(marvelRemoteMarvelHeroesDataSource, localMarvelDataSource, settingsManager)

    @Provides
    @Singleton
    fun provideHeroDatabase(context: Context): HeroDatabase = Room.databaseBuilder(context, HeroDatabase::class.java, "heroe_db").build()

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideSettingsManager(sharedPreferences: SharedPreferences) : SettingsManager =
            SettingsManager(sharedPreferences)
}