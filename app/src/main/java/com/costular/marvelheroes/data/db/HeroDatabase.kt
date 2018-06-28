package com.costular.marvelheroes.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.costular.marvelheroes.domain.model.MarvelHeroEntity

@Database(entities = [MarvelHeroEntity::class], version = 1)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun getHeroDao(): HeroDao
}