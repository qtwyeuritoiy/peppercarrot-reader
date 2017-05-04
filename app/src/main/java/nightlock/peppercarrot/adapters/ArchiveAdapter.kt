package nightlock.peppercarrot.adapters

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.viewholders.EpisodeViewHolder

/**
 * Created by nightlock on 5/2/17.
 */

class ArchiveAdapter(context: Context): RecyclerView.Adapter<EpisodeViewHolder>() {
    val sharedPref = context.getSharedPreferences("archive", Context.MODE_PRIVATE) as SharedPreferences

    override fun getItemCount(): Int {
        return sharedPref.getInt("archiveCount", 0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.Factory.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = sharedPref.getString("ep$position","")
        val link = "https://www.peppercarrot.com/0_sources/$episode/low-res/Pepper-and-Carrot_by-" +
                "David-Revoy_E${if (position < 9) "0$position" else "$position"}.jpg"
        holder.onBind(link)

    }

}
