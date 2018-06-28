package com.costular.marvelheroes.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.costular.marvelheroes.data.db.HeroDatabase
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelDatasource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by costular on 17/03/2018.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroMapper = MarvelHeroMapper()

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSoruce(marvelHeroesService: MarvelHeroesService, marvelHeroMapper: MarvelHeroMapper)
            : RemoteMarvelHeroesDataSource =
            RemoteMarvelHeroesDataSource(marvelHeroesService, marvelHeroMapper)

    @Provides
    @Singleton
    fun provideLocalMarvelHeroesDataSource(heroeDatabase: HeroDatabase) : LocalMarvelDatasource = LocalMarvelDatasource(heroeDatabase)

    @Provides
    @Singleton
    fun provideMarvelHeroesRepository(
            marvelRemoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
            localMarvelDatasource: LocalMarvelDatasource): MarvelHeroesRepositoryImpl =
            MarvelHeroesRepositoryImpl(marvelRemoteMarvelHeroesDataSource, localMarvelDatasource)

    @Provides
    @Singleton
    fun provideHeroeDatabase(context: Context): HeroDatabase = Room.databaseBuilder(context, HeroDatabase::class.java, "heroe_db").build()
}