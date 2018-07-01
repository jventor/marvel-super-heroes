package com.costular.marvelheroes.di.modules

import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.di.scopes.PerActivity
import com.costular.marvelheroes.domain.usecase.GetMarvelHeroesList
import com.costular.marvelheroes.presentation.heroeslist.HeroesListActivity
import dagger.Module
import dagger.Provides

@Module
class GetMarvelHeroesListModule(private val view: HeroesListActivity) {

    @Provides
    @PerActivity
    fun provideGetListHeroesUseCase(marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl): GetMarvelHeroesList =
            GetMarvelHeroesList(marvelHeroesRepositoryImpl)
}