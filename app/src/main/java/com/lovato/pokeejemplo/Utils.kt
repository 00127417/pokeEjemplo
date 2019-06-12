package com.lovato.pokeejemplo

import android.net.Uri
import java.net.URL

class Utils{
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon?offset=1&limit=964"

        fun buildURL() = URL(
            Uri.parse(BASE_URL)
                .buildUpon()
                .build().toString()
        )

        fun getHTTPResult(url: URL) = url.readText()

    }
}