package nightlock.peppercarrot.utils

/**
 * Miscellaneous utility functions.
 * Created by Jihoon Kim on 21/07/17.
 */

fun getCoverImageUrl(episode: Episode) = "https://www.peppercarrot.com/0_sources/${episode.name}/low-res/" +
        "${getPreferredLanguage(episode.supported_languages)}_Pepper-and-Carrot_by-David-Revoy_" +
        "E${if (episode.index < 9) "0" else ""}${episode.index + 1}.jpg"

fun getComicImageUrl(episode: Episode, index: Int) = "https://www.peppercarrot.com/0_sources/${episode.name}" +
        "/low-res/${getPreferredLanguage(episode.supported_languages)}_Pepper-and-Carrot_by-David-Revoy_" +
        "E${if (episode.index < 9) "0" else ""}${episode.index + 1}P${if (index < 10) "0" else ""}$index.jpg"
