package nightlock.peppercarrot.viewholders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import nightlock.peppercarrot.R
import nightlock.peppercarrot.activities.ComicViewerActivity
import nightlock.peppercarrot.utils.Episode

/**
 * ViewHolder for containing the episode cover.
 * Created by nightlock on 5/2/17.
 */

class EpisodeViewHolder private constructor(v: View): RecyclerView.ViewHolder(v){
    val imageView : ImageView =
            v.findViewById(R.id.episode_image) as ImageView
    val context = imageView.context!!
    var ep = 0
    init {
        imageView.setOnClickListener {
            val intent = Intent(context, ComicViewerActivity::class.java)
            intent.putExtra("ep", ep)
            context.startActivity(intent)
        }
    }

    fun onBind(episode: Episode) {
        val name = episode.name
        val index = episode.index + 1
        val locale = "en" //Temporary workaround; should be omitted upon release.
        val link = "https://www.peppercarrot.com/0_sources/$name/low-res/$locale" +
                "_Pepper-and-Carrot_by-David-Revoy_E${if (index < 10) "0" else ""}$index.jpg"
        Glide
                .with(context)
                .load(link)
                .fitCenter()
                .into(imageView)
        this.ep = episode.index
    }

    companion object {
        fun newInstance(viewGroup: ViewGroup) : EpisodeViewHolder {
            val view = LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.episode_card, viewGroup, false)
            return EpisodeViewHolder(view)
        }
    }
}
