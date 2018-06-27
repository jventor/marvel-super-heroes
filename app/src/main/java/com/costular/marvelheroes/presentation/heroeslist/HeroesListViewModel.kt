package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.mvvm.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(private val marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl) : BaseViewModel(){

    val heroesListState : MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState : MutableLiveData<Boolean> = MutableLiveData()

    fun loadHeroesList(){
        marvelHeroesRepositoryImpl.getMarvelHeroesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoadingState.postValue(true) }
                .doOnTerminate { isLoadingState.postValue(false) }
                .subscribeBy(
                        onNext = {
                            heroesListState.value = it
                        },
                        onError = {},
                        onComplete = { }
                )
                .addTo(compositeDisposable)

    }
}