package nightlock.peppercarrot.adapters

import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.viewholders.EpisodeViewHolder

/**
 * Created by nightlock on 5/2/17.
 */

class ArchiveAdapter(context: Context): RecyclerView.Adapter<EpisodeViewHolder>() {
    init{
        val sharedPref = context.getSharedPreferences("archive", Context.MODE_PRIVATE) as SharedPreferences
    }

    override fun getItemCount(): Int {
        return sharedPref.getInt("archiveCount")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.Factory.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {

        val link = sharedPref.getString("ep$position")
        holder.onBind(link)

    }

}
