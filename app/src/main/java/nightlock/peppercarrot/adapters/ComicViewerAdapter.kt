package nightlock.peppercarrot.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.utils.ArchiveDataManager
import nightlock.peppercarrot.viewholders.ComicViewHolder

/**
 * Adapter for Displaying ComicViewHolder
 * Created by nightlock on 5/7/17.
 */

class ComicViewerAdapter(val context: Context, var index: Int) :
        RecyclerView.Adapter<ComicViewHolder>() {
    val db = ArchiveDataManager(context)

    override fun onBindViewHolder(holder: ComicViewHolder?, delta: Int) {
        val episode = db.getEpisode(index)
        val index = episode.index + 1
        val name = episode.name
        val locale = "en"
        val link = "https://www.peppercarrot.com/0_sources/$name/low-res/$locale" +
                "_Pepper-and-Carrot_by-David-Revoy_E${if (index < 10) "0" else ""}${index}" +
                "P${if (delta < 10) "0" else ""}$delta.jpg"
        holder?.onBind(link)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder =
            ComicViewHolder.newInstance(parent!!)

    override fun getItemCount() = db.getEpisode(index).pages
}