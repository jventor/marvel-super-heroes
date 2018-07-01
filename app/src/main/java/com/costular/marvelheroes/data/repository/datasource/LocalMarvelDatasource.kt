package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.db.HeroDatabase

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class LocalMarvelDatasource(val heroDatabase: HeroDatabase): MarvelHeroesDataSource {
    override fun getMarvelHeroesList(): Single<List<MarvelHeroEntity>> =
        heroDatabase.getHeroDao()
                .getAllHeroes()
               .toSingle()

    fun saveMarvelHeroes(heroes: List<MarvelHeroEntity>){
        Observable.fromCallable { heroDatabase.getHeroDao().insertAll(heroes)}
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun updateMarvelHero(hero: MarvelHeroEntity) {
        Observable.fromCallable { heroDatabase.getHeroDao().updateMarvelHero(hero) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

}