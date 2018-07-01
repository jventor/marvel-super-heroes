package com.costular.marvelheroes.domain.model

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "heroes")
@SuppressLint("ParcelCreator")
@Parcelize
data class MarvelHeroEntity(
        @PrimaryKey
        var name: String = "",
        var photoUrl: String = "",
        var realName: String = "",
        var height: String = "",
        var power: String = "",
        var abilities: String = "",
        var favorite: Boolean = false,
        var rating: Float = 0.0F
) : Parcelable