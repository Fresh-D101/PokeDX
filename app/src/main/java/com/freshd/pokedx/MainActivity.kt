package com.freshd.pokedx

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.freshd.pokedx.common.Common
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  Hier ein paar Anmerkungen zu deinem Code, meld dich bei Rückfragen einfach :)
 *
 *  - du hast sehr viele !! Aufrufe -> falls etwas wider erwarten doch null sein sollte, dann gibts nen crash
 *       -> Kotlin hat nicht ohne Grund eine Null-Safety. Die sollte nicht umgangen werden, indem man alles mit null initialisiert oder !! Aufrufe nutzt. Das sollte die Ausnahme sein
 *       -> Initialisiere Werte mit default values wenn möglich. Mach z. B. aus einer "val whatever: List<String>? = null" ne "val whatever = emptyList<String>() draus. Dann crash im Zweifelsfall nix, sondern is einfach leer
 *       -> schau dir die Funktionen apply, let, also, with, run an, z. B. hier:
 *          https://medium.com/@elye.project/mastering-kotlin-standard-functions-run-with-let-also-and-apply-9cd334b0ef84
 *  - Package names werden klein geschrieben
 *  - mit Kotlin sind findViewById calls zu 95% überflüssig, da direkt auf die views zugegriffen werden kann
 *  - Fragmente sollten über eine newInstance()-Methode erstellt werden, nicht über den normalen Konstruktor. Siehe "ExampleBlankFragment.kt"
 *  - BroadCastReceiver sollten nicht so benutzt werden, wie du sie benutzt hast
 *      -> schau dir diese beiden Alternativen an, wenn du etwas zurück zu etwas anderem Kommunizieren willst:
 *  - Callbacks (per Interface)
 *  - Lambda Funktionen, die man als Parameter übergibt
 *      -> Die Navigation mit Fragments wird außerdem viel einfacher, wenn man den neuen Navigation Component/Graph von Android benutzt. Hierdurch ändern sich einigermaßen viel, wäre aber inzwischen der way to go
 *  - Variablentypen werden impizit durch die Zuweisung erkannt, d. h. statt "val retrofit: Retrofit = RetrofitClient.instance" kann mal einfach "val retrofit = RetrofitClient.instance" schreiben
 *  - Strings werden nie hardcoded. Die kommen __IMMER__ in die strings.xml, auch wenns nur eine Sprache gibt. Falls du Beispieltext in einer XML beim layouting brauchst, dann verwende statt "android:text="bla"" einfach "tools:text="bla". Dieser Text wird nur im Designer angezeigt.
 *  - String Konkatenation (siehe height/weight von Pokemon im DetailFragment) sollte vermieden werden, es gibt Strings mit Variablen
 *  - ich konnte nicht alles überarbeiten, aber meine Änderungen sollten dir einen kleinen Einblick geben :)
 *  Fürs erste mal Android (soweit ichs weiss) is es aber durchaus nice, mach weiter so!
 */
class MainActivity : AppCompatActivity() {

    private lateinit var detailedFragment: PokemonDetail

    private val showDetail = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action?.equals(Common.KEY_ENABLE_HOME) == true) {

                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)

                // Replace Fragment
                val position: Int = intent.getIntExtra("position", -1)
                val bundle = Bundle()
                bundle.putInt("position", position)
                detailedFragment.arguments = bundle

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.list_pokemon_fragment, detailedFragment)
                    addToBackStack("detail")
                    commit()
                }

                // Set Pokemon Name for toolbar
                toolbar.title = Common.pokemonList[position].name
            }
        }
    }

    private val showEvolution = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action?.toString() == Common.KEY_NUM_EVOLUTION) {

                //Replace Fragment
                val num = intent.getStringExtra("num")
                val bundle = Bundle()
                bundle.putString("num", num)
                detailedFragment.arguments = bundle

                supportFragmentManager.beginTransaction().apply {
                    remove(detailedFragment)
                    replace(R.id.list_pokemon_fragment, detailedFragment)
                    addToBackStack("detail")
                    commit()
                }

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
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showEvolution, IntentFilter(Common.KEY_NUM_EVOLUTION))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
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

    private fun goBackToList() {
        toolbar.title = "Pokemon List"

        // Clear all fragment in stack with name 'detail'
        supportFragmentManager.popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        with(supportActionBar) {
            this?.setDisplayHomeAsUpEnabled(false)
            this?.setDisplayShowHomeEnabled(false)
        }

    }
}
