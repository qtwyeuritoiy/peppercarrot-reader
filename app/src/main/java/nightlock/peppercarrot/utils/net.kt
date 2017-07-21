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