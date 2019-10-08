package com.freshd.pokedx


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freshd.pokedx.adapter.PokemonEvolutionAdapter
import com.freshd.pokedx.adapter.PokemonTypeAdapter
import com.freshd.pokedx.common.Common
import com.freshd.pokedx.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.pokemon_image

class PokemonDetail : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        with(itemView) {
            setUpRecyclerView(recycler_type)
            setUpRecyclerView(recycler_weakness)
            setUpRecyclerView(recycler_prev_evolution)
            setUpRecyclerView(recycler_next_evolution)

            setDetailPokemon(itemView)

            return this
        }

    }

    private fun setUpRecyclerView(view: RecyclerView) = with(view) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setDetailPokemon(itemView: View) = with(itemView){
        val pokemon = arguments?.getInt("num")?.let {
            Common.pokemonList[arguments!!.getInt("position")]
        } ?: Common.findPokemonByNum(arguments?.getString("num"))

        // Load image

        pokemon?.apply {
            Glide.with(context).load(pokemon.img).into(pokemon_image)

            this@with.name.text = name
            pokemon_height.text = context.getString(R.string.height, pokemon.height)
            pokemon_weight.text = context.getString(R.string.weight, pokemon.weight)

            recycler_type.adapter = PokemonTypeAdapter(context, pokemon.type)
            recycler_weakness.adapter = PokemonTypeAdapter(activity!!, pokemon.weaknesses)
            recycler_prev_evolution.adapter = PokemonEvolutionAdapter(itemView.context, pokemon.prev_evolution)
            recycler_next_evolution.adapter = PokemonEvolutionAdapter(itemView.context, pokemon.next_evolution)
        }
    }


}
