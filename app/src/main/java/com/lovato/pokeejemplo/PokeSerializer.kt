package com.lovato.pokeejemplo

import org.json.JSONArray
import org.json.JSONObject

class PokeSerializer{
    companion object {
        fun parseCoins(coinsText: String): List<Pokemon>{
            var coinsJSON = JSONArray(coinsText)
            return MutableList(coinsJSON.length()-1){
                parseCoin(coinsJSON[it].toString())
            }
        }

        fun parseCoin(coinsText: String): Pokemon{
            val coinJSON = JSONObject(coinsText)
            return with(coinJSON){
                Pokemon(getString("name"))

            }
        }
    }
}