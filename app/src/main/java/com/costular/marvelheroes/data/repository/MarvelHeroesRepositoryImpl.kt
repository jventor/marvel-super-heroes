package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.repository.datasource.LocalMarvelDatasource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localMarvelDatasource: LocalMarvelDatasource)
    : MarvelHeroesRepository {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
        getMarvelHeroesFromDb()

    private fun getMarvelHeroesFromDb(): Flowable<List<MarvelHeroEntity>> = localMarvelDatasource.getMarvelHeroesList()

    private fun getMarvelHeroesFromApi(): Flowable<List<MarvelHeroEntity>>  = remoteMarvelHeroesDataSource.getMarvelHeroesList()
            .doOnNext { localMarvelDatasource.saveMarvelHeroes(it) }

    fun updateMarvelHero (heroe: MarvelHeroEntity) {
        localMarvelDatasource.updateMarvelHero(heroe)
    }

}