package nightlock.peppercarrot.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

/**
 * Created by qtwye on 2017-05-03.
 */

fun getEpisodeList(): List<String> {
    val rawEpisodeList = getHtmlFromUrl("https://www.peppercarrot.com/0_sources/.episodes-list.md")
    return rawEpisodeList.trim().lines()
}

fun getHtmlFromUrl (url: String) : String {
    var client = OkHttpClient ()
    var request = Request.Builder()
            .url (url)
            .build ()

    var response = client.newCall (request).execute ()
    return response.body ().string ()
}

fun getAvailablePreferredLocale(episode: String): String {
    val rawEpisode = getHtmlFromUrl("https://www.peppercarrot.com/0_sources/$episode/low-res/single-page/")

    for (locale: Locale in Locale.getAvailableLocales()){
        if (rawEpisode.contains(locale.language))
            return locale.language
    }

    return "en"
}

fun requestAsyncDownload(context: Context, dest: String, url: String, mimeType: String) : Long {
    var uri = Uri.parse(Uri.decode(url))
    var downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    var request = DownloadManager.Request(uri)
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setTitle("Downloading Comic...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setDestinationInExternalFilesDir(context, "", dest)
            .setMimeType(mimeType)

    return downloadManager.enqueue(request)
}