package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable

interface MarvelHeroesRepository {

    fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>>

}