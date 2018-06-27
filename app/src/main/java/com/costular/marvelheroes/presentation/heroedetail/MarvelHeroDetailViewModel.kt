package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.mvvm.BaseViewModel

class MarvelHeroDetailViewModel : BaseViewModel(){
    var heroeState: MutableLiveData<MarvelHeroEntity> = MutableLiveData()
}