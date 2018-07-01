package com.costular.marvelheroes.data.repository.datasource


import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Single


interface MarvelHeroesDataSource {

    fun getMarvelHeroesList(): Single<List<MarvelHeroEntity>>

}