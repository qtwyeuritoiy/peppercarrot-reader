package nightlock.peppercarrot.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.florent37.beautifulparallax.ParallaxViewController
import nightlock.peppercarrot.viewholders.EpisodeViewHolder

/**
 * Created by nightlock on 5/2/17.
 */

class ArchiveAdapter: RecyclerView.Adapter<EpisodeViewHolder>() {
    val linkList = ArrayList<String>()

    override fun getItemCount(): Int {
        return linkList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.Factory.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.onBind(linkList[position])
    }

    fun addAndNotify(link: String) {
        linkList.add(link)
        notifyItemInserted(itemCount-1)
    }

}
