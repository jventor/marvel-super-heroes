package com.costular.marvelheroes.data.repository.datasource


import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Single


/**
 * Created by costular on 17/03/2018.
 */
interface MarvelHeroesDataSource {

    fun getMarvelHeroesList(): Single<List<MarvelHeroEntity>>

}