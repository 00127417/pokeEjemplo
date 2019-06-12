package com.lovato.pokeejemplo

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var viewAdapter: PokeAdapter
    lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokeAdapter(listOf<Pokemon>())

        rv_pokemons.apply {
            adapter = viewAdapter
            layoutManager = viewManager
        }

        PokeFetch().execute()
    }

    inner class PokeFetch : AsyncTask<Unit, Unit, List<Pokemon>>() {
        override fun doInBackground(vararg params: Unit?): List<Pokemon> {
            val url = Utils.buildURL()
            val resultString = Utils.getHTTPResult(url)

            val resultJSON = JSONObject(resultString)

            return try{
                PokeSerializer.parseCoins(
                    resultJSON.getJSONArray("results").toString())
            } catch (e: IOException) {
                e.printStackTrace()
                listOf<Pokemon>()
            }
        }

        override fun onPostExecute(result: List<Pokemon>) {
            if(result.isNotEmpty()){
                viewAdapter.setData(result)

            }else{
                Snackbar.make(rv_pokemons,"No se pudo obtener monedas",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
