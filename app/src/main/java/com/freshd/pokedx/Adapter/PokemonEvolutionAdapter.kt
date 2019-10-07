package com.freshd.pokedx.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.freshd.pokedx.Common.Common
import com.freshd.pokedx.Model.Evolution
import com.freshd.pokedx.R
import com.robertlevonyan.views.chip.Chip
import kotlinx.android.synthetic.main.chip_item.view.*

class PokemonEvolutionAdapter(internal var context : Context, var evolutionList : List<Evolution>?) : RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder>() {

    init {
        if(evolutionList == null){
            evolutionList = ArrayList()
        }
    }

    inner class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        internal var chip: Chip = itemView.chip

        fun setItemClickListener(onClickListener: View.OnClickListener){
            chip.setOnClickListener{ view -> onClickListener.onClick(view)}
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return evolutionList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.text = evolutionList!![position].name
        var pokemonEvolution = Common.findPokemonByNum(evolutionList!![position].num)
        holder.chip.chipBackgroundColor = Common.getColorByType(pokemonEvolution!!.type!![0])

        holder.setItemClickListener(View.OnClickListener {
            LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(Common.KEY_NUM_EVOLUTION).putExtra("num", evolutionList!![position].num))
        })
    }
}