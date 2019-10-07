package com.freshd.pokedx.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL:String = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/"

    private var ourInstance:Retrofit? = null

    val instance:Retrofit
    get(){
        if(ourInstance == null){
            ourInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return ourInstance!!
    }

}