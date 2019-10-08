package com.freshd.pokedx


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freshd.pokedx.adapter.PokemonListAdapter
import com.freshd.pokedx.common.Common
import com.freshd.pokedx.retrofit.IPokemonList
import com.freshd.pokedx.retrofit.RetrofitClient
import com.freshd.pokedx.common.ItemOffsetDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.view.*

class PokemonList : Fragment() {

    private var compositeDisposable = CompositeDisposable()
    private var iPokemonList: IPokemonList

    private lateinit var recyclerView: RecyclerView

    init {
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recyclerView = itemView.pokemon_recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val itemDecoration = ItemOffsetDecoration(itemView.context, R.dimen.spacing)
        recyclerView.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData(){
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon
                val adapter = PokemonListAdapter(activity!!, Common.pokemonList)

                recyclerView.adapter = adapter
            }
        )
    }

}
