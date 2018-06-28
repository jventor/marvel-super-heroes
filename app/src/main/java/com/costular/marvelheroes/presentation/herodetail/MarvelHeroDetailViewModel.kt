package com.costular.marvelheroes.presentation.herodetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.mvvm.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MarvelHeroDetailViewModel @Inject constructor(private val marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl) : BaseViewModel(){
    var heroState: MutableLiveData<MarvelHeroEntity> = MutableLiveData()

    fun updateMarvelHero(hero: MarvelHeroEntity){
        Observable.fromCallable {  marvelHeroesRepositoryImpl.updateMarvelHero(hero) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    heroState.value = hero
                }
                .addTo(compositeDisposable)
    }
}