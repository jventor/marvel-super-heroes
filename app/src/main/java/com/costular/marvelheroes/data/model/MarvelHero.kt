package com.costular.marvelheroes.data.model

import com.google.gson.annotations.SerializedName

data class MarvelHero(
        val name: String = "",
        @SerializedName("photo")
        val photoUrl: String = "",
        val realName: String = "",
        val height: String = "",
        val power: String = "",
        val abilities: String = "",
        val groups: String = ""
)