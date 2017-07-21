package nightlock.peppercarrot.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.utils.Episode
import nightlock.peppercarrot.viewholders.EpisodeViewHolder

/**
 * Adapter for Displaying EpisodeViewHolder
 * Created by Jihoon Kim on 5/2/17.
 */

class ArchiveAdapter: RecyclerView.Adapter<EpisodeViewHolder>() {
    val episodeList = ArrayList<Episode>()

    override fun getItemCount() = episodeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            EpisodeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) =
            holder.onBind(episodeList[position])

    fun addAndNotify(episode: Episode) {
        episodeList.add(episode)
        notifyItemInserted(itemCount-1)
    }

}
