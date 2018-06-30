package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.repository.datasource.LocalMarvelDatasource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localMarvelDatasource: LocalMarvelDatasource)
    : MarvelHeroesRepository {

    private fun getMarvelHeroesFromDb(): Flowable<List<MarvelHeroEntity>> = localMarvelDatasource.getMarvelHeroesList()

    fun updateMarvelHero (heroe: MarvelHeroEntity) {
        localMarvelDatasource.updateMarvelHero(heroe)
    }

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> {
        val resultObservable = remoteMarvelHeroesDataSource.getMarvelHeroesList()

        resultObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    localMarvelDatasource.saveMarvelHeroes(it)
                }
        return getMarvelHeroesFromDb()
    }
}