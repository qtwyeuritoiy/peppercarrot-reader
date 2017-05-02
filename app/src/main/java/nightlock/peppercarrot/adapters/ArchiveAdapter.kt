package nightlock.peppercarrot.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.viewholders.EpisodeViewHolder

/**
 * Created by nightlock on 5/2/17.
 */

class ArchiveAdapter : RecyclerView.Adapter<EpisodeViewHolder>() {
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.Factory.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.onBind(position)

    }

}