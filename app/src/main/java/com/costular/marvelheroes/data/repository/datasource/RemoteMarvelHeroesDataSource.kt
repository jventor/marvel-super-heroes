package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Single


class RemoteMarvelHeroesDataSource(private val marvelHeroesService: MarvelHeroesService,
                                    private val marvelHeroMapper: MarvelHeroMapper):
        MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Single<List<MarvelHeroEntity>> =
            marvelHeroesService.getMarvelHeroesList()
                    .map { it.superheroes }
                    .map {
                        marvelHeroMapper.transformList(it) }
}