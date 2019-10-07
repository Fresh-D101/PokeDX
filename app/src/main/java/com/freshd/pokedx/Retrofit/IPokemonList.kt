package com.freshd.pokedx.Retrofit

import com.freshd.pokedx.Model.PokeDex
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon: Observable<PokeDex>
}