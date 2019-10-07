package com.freshd.pokedx.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.freshd.pokedx.Common.Common
import com.freshd.pokedx.R
import com.robertlevonyan.views.chip.Chip
import kotlinx.android.synthetic.main.chip_item.view.*

class PokemonTypeAdapter(internal var context: Context, internal var typeList:List<String>): RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        internal var chip:Chip = itemView.chip
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var type = typeList[position]
        holder.chip.text = type
        holder.chip.chipBackgroundColor = Common.getColorByType(type)
    }
}

