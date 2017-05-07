package nightlock.peppercarrot.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.viewholders.ComicViewHolder

/**
 * Created by nightlock on 5/7/17.
 */

class ComicViewerAdapter(val pageCount: Int, episode: String) :
        RecyclerView.Adapter<ComicViewHolder>() {
    init{

    }

    override fun onBindViewHolder(holder: ComicViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder =
            ComicViewHolder.Factory.newInstance(parent!!)

    override fun getItemCount() = pageCount
}