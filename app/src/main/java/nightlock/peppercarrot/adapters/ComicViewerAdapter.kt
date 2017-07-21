package nightlock.peppercarrot.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.utils.ArchiveDataManager
import nightlock.peppercarrot.utils.getComicImageUrl
import nightlock.peppercarrot.viewholders.ComicViewHolder

/**
 * Adapter for Displaying ComicViewHolder
 * Created by Jihoon Kim on 5/7/17.
 */

class ComicViewerAdapter(val context: Context, var index: Int) :
        RecyclerView.Adapter<ComicViewHolder>() {
    val db = ArchiveDataManager(context)

    override fun onBindViewHolder(holder: ComicViewHolder?, delta: Int) {
        val episode = db.getEpisode(index)
        holder?.onBind(getComicImageUrl(episode, delta))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder =
            ComicViewHolder.newInstance(parent!!)

    override fun getItemCount() = db.getEpisode(index).pages
}