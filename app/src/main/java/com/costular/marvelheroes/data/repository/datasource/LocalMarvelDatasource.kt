package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.db.HeroeDatabase

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable


class LocalMarvelDatasource(val heroeDatabase: HeroeDatabase): MarvelHeroesDataSource {
    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
        heroeDatabase.getHeroeDao()
                .getAllHeroes()
                .toFlowable()
}