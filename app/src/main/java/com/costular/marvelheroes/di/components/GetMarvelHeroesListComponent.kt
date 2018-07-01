package com.costular.marvelheroes.di.components

import com.costular.marvelheroes.di.modules.GetMarvelHeroesListModule
import com.costular.marvelheroes.di.scopes.PerActivity
import com.costular.marvelheroes.presentation.herodetail.MarvelHeroDetailActivity
import com.costular.marvelheroes.presentation.heroeslist.HeroesListActivity
import dagger.Component

@PerActivity
@Component(modules = [GetMarvelHeroesListModule::class], dependencies = [ApplicationComponent::class])
interface GetMarvelHeroesListComponent {

    fun inject(marvelListActivity: HeroesListActivity)
    fun inject(marvelHeroDetailActivity: MarvelHeroDetailActivity)

}