package com.freshd.pokedx.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freshd.pokedx.common.Common
import com.freshd.pokedx.model.Pokemon
import com.freshd.pokedx.R
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonListAdapter(internal var context: Context,
                         internal var pokemonList:List<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        internal var pokemonImage:ImageView = itemView.pokemon_image
        internal var pokemonText:TextView = itemView.pokemon_name

        fun setItemClickListener(onClickListener: View.OnClickListener){
            itemView.setOnClickListener{view -> onClickListener.onClick(view)}

            /*itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v : View?){
                    Toast.makeText(null, "Hello", Toast.LENGTH_SHORT)
                }
            })*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(pokemonList[position].img).into(holder.pokemonImage)
        holder.pokemonText.text = pokemonList[position].name
        holder.setItemClickListener(View.OnClickListener {
            //Toast.makeText(context, "Clicked on " + pokemonList[position].name, Toast.LENGTH_SHORT).show()
            LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(Common.KEY_ENABLE_HOME).putExtra("position", position))
        })
    }
}

