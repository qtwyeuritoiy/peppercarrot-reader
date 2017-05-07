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

/**
 * Created by nightlock on 5/2/17.
 */

class EpisodeViewHolder private constructor(v: View): RecyclerView.ViewHolder(v){
    val imageView : ImageView =
            v.findViewById(R.id.episode_image) as ImageView
    val context = imageView.context
    var ep = 0
    init {
        imageView.setOnClickListener {
            val intent = Intent(context, ComicViewerActivity::class.java)
            intent.putExtra("ep", ep)
            context.startActivity(intent)
        }
    }

    object Factory{
        fun newInstance(viewGroup: ViewGroup) : EpisodeViewHolder {
            val view = LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.episode_card, viewGroup, false)
            return EpisodeViewHolder(view)
        }
    }

    fun onBind(imgLink: String, ep: Int) {
        Glide
                .with(context)
                .load(imgLink)
                .fitCenter()
                .into(imageView)
        this.ep = ep
    }
}
