package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.repository.datasource.LocalMarvelDatasource
import com.costular.marvelheroes.data.repository.datasource.RemoteMarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.SettingsManager
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val remoteMarvelHeroesDataSource: RemoteMarvelHeroesDataSource,
                                 private val localMarvelDatasource: LocalMarvelDatasource,
                                 private val settingsManager: SettingsManager)
    : MarvelHeroesRepository {

    private fun getMarvelHeroesFromDb(): Flowable<List<MarvelHeroEntity>> = localMarvelDatasource.getMarvelHeroesList().toFlowable()

    fun updateMarvelHero (hero: MarvelHeroEntity) {
        localMarvelDatasource.updateMarvelHero(hero)
    }

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> {
        if (!settingsManager.firstLoad) {
            remoteMarvelHeroesDataSource.getMarvelHeroesList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorResumeNext {
                        Single.just(emptyList())
                    }
                    .doOnSuccess {
                        localMarvelDatasource.saveMarvelHeroes(it)
                    }

            return getMarvelHeroesFromDb()
        }
        return remoteMarvelHeroesDataSource.getMarvelHeroesList().doOnSuccess {
            localMarvelDatasource.saveMarvelHeroes(it)
        }.toFlowable()
    }
}