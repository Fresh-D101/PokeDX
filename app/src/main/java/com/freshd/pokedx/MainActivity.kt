package com.freshd.pokedx

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.freshd.pokedx.Common.Common
import com.freshd.pokedx.Model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var detailedFragment:PokemonDetail

    private val showDetail = object:BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent!!.action!!.toString() == Common.KEY_ENABLE_HOME){

                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)

                //Replace Fragment
                val position:Int = intent.getIntExtra("position", -1)
                val bundle = Bundle()
                bundle.putInt("position", position)
                detailedFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailedFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()

                //Set Pokemon Name for toolbar
                toolbar.title = Common.pokemonList[position].name
            }
        }
    }

    private val showEvolution = object:BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent!!.action!!.toString() == Common.KEY_NUM_EVOLUTION){

                //Replace Fragment
                val num = intent.getStringExtra("num")
                val bundle = Bundle()
                bundle.putString("num", num)
                detailedFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.remove(detailedFragment)
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailedFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()

                //Set Pokemon Name for toolbar
                toolbar.title = Common.findPokemonByNum(num)!!.name
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Pokemon List"
        setSupportActionBar(toolbar)

        detailedFragment = PokemonDetail()

        //Register Broadcast
        LocalBroadcastManager.getInstance(this).registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))
        LocalBroadcastManager.getInstance(this).registerReceiver(showEvolution, IntentFilter(Common.KEY_NUM_EVOLUTION))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            android.R.id.home -> {
                goBackToList()
            }
        }

        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        goBackToList()
    }

    private fun goBackToList(){
        toolbar.title = "Pokemon List"

        //Clear all fragment in stack with name 'detail'
        supportFragmentManager.popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
    }
}
