package com.freshd.pokedx


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freshd.pokedx.Adapter.PokemonEvolutionAdapter
import com.freshd.pokedx.Adapter.PokemonTypeAdapter
import com.freshd.pokedx.Common.Common
import com.freshd.pokedx.Model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.pokemon_image
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonDetail : Fragment() {

    lateinit var recycler_type:RecyclerView
    lateinit var recycler_weakness:RecyclerView
    lateinit var recycler_prev_evolution:RecyclerView
    lateinit var recycler_next_evolution:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        val pokemon:Pokemon?
        val num = arguments!!.getString("num")
        pokemon = if(num == null){
            Common.pokemonList[arguments!!.getInt("position")]
        } else{
            Common.findPokemonByNum(num)
        }

        recycler_type = itemView.recycler_type
        setUpRecyclerView(recycler_type)

        recycler_weakness = itemView.findViewById(R.id.recycler_weakness)
        setUpRecyclerView(recycler_weakness)

        recycler_prev_evolution = itemView.findViewById(R.id.recycler_prev_evolution)
        setUpRecyclerView(recycler_prev_evolution)

        recycler_next_evolution = itemView.findViewById(R.id.recycler_next_evolution)
        setUpRecyclerView(recycler_next_evolution)

        setDetailPokemon(pokemon, itemView)

        return itemView
    }

    private fun setUpRecyclerView(view:RecyclerView){
        view.setHasFixedSize(true)
        view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setDetailPokemon(pokemon: Pokemon?, itemView: View){
        //Load image
        Glide.with(activity!!).load(pokemon!!.img).into(itemView.pokemon_image)

        itemView.name.text = pokemon.name
        itemView.pokemon_height.text = "Height: " + pokemon.height
        itemView.pokemon_weight.text = "Weight: " + pokemon.weight

        val typeAdapter = PokemonTypeAdapter(activity!!, pokemon.type!!)
        recycler_type.adapter = typeAdapter

        val weaknessAdapter = PokemonTypeAdapter(activity!!, pokemon.weaknesses!!)
        recycler_weakness.adapter = weaknessAdapter

        val prevEvolutionAdapter = PokemonEvolutionAdapter(activity!!, pokemon.prev_evolution)
        recycler_prev_evolution.adapter = prevEvolutionAdapter

        val nextEvolutionAdapter = PokemonEvolutionAdapter(activity!!, pokemon.next_evolution)
        recycler_next_evolution.adapter = nextEvolutionAdapter
    }


}
