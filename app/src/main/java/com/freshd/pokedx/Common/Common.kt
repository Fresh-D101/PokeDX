package com.freshd.pokedx.common

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.freshd.pokedx.model.Pokemon
import com.freshd.pokedx.R

object Common {
    const val KEY_ENABLE_HOME = "position"
    const val KEY_NUM_EVOLUTION = "evolution"

    var pokemonList = emptyList<Pokemon>()

    fun findPokemonByNum(num: String?): Pokemon? {
        for(pokemon in pokemonList) {
            if(pokemon.num == num)
                return pokemon
        }
        return null
    }

    fun getColorByType(context: Context, type: String): Int {
        with(context) {
            when (type) {
                "Normal"    -> return ResourcesCompat.getColor(resources, R.color.Normal, null)
                "Dragon"    -> return ResourcesCompat.getColor(resources, R.color.Dragon, null)
                "Psychic"   -> return ResourcesCompat.getColor(resources, R.color.Psychic, null)
                "Electric"  -> return ResourcesCompat.getColor(resources, R.color.Electric, null)
                "Ground"    -> return ResourcesCompat.getColor(resources, R.color.Ground, null)
                "Grass"     -> return ResourcesCompat.getColor(resources, R.color.Grass, null)
                "Poison"    -> return ResourcesCompat.getColor(resources, R.color.Poison, null)
                "Steel"     -> return ResourcesCompat.getColor(resources, R.color.Steel, null)
                "Fairy"     -> return ResourcesCompat.getColor(resources, R.color.Fairy, null)
                "Fire"      -> return ResourcesCompat.getColor(resources, R.color.Fire, null)
                "Fight"     -> return ResourcesCompat.getColor(resources, R.color.Fight, null)
                "Bug"       -> return ResourcesCompat.getColor(resources, R.color.Bug, null)
                "Ghost"     -> return ResourcesCompat.getColor(resources, R.color.Ghost, null)
                "Dark"      -> return ResourcesCompat.getColor(resources, R.color.Dark, null)
                "Ice"       -> return ResourcesCompat.getColor(resources, R.color.Ice, null)
                "Water"     -> return ResourcesCompat.getColor(resources, R.color.Water, null)
                else        -> return ResourcesCompat.getColor(resources, R.color.Misc, null)
            }
        }
    }
}