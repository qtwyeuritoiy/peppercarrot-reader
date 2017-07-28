/*
 * Copyright (C) 2017 Jihoon Kim <imsesaok@gmail.com, imsesaok@tuta.io>
 *
 * This file is part of Reader for Pepper&Carrot.
 *
 * Reader for Pepper&Carrot is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nightlock.peppercarrot.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

/**
 * Utility functions for network tasks.
 * Created by Jihoon Kim on 2017-05-03.
 */

fun getEpisodeList(): List<Episode> {
    val rawEpisodeList = getFromUrl("https://www.peppercarrot.com/0_sources/episode.json")
    val episodeJson = JSONArray(rawEpisodeList)
    val episodeList = ArrayList<Episode>()

    for (i in 0..episodeJson.length() - 1) {
        val episode = episodeJson.getJSONObject(i)

        val name = episode.getString("name")
        val page = episode.getInt("total_page")
        val languageJsonList = episode.getJSONArray("translated_languages")

        val languageList = ArrayList<String>()
        for (j in 0..languageJsonList.length() - 1)
            languageList += languageJsonList.getString(j)

        episodeList += Episode(i, name, page, languageList)
    }
    return episodeList
}

fun getFromUrl(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url (url)
            .build ()

    val response = client.newCall(request).execute()
    return response.body ().string ()
}