package com.costular.marvelheroes.data.db

import android.arch.persistence.room.*
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
abstract class HeroDao {
    @Query("SELECT * FROM heroes")
    abstract fun getAllHeroes(): Maybe<List<MarvelHeroEntity>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAll(heroes: List<MarvelHeroEntity>)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateMarvelHero(hero: MarvelHeroEntity) : Int

    @Query("DELETE FROM heroes")
    abstract fun deleteAllHeroes()

    @Transaction
    open fun updateMarvelHeroes(heroes: List<MarvelHeroEntity>){
        insertAll(heroes)
    }

}