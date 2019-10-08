package com.freshd.pokedx.retrofit

import com.freshd.pokedx.model.PokeDex
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon: Observable<PokeDex>
}