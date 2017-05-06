package nightlock.peppercarrot.viewholders

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import nightlock.peppercarrot.R

/**
 * Created by nightlock on 5/2/17.
 */

class EpisodeViewHolder private constructor(v: View): RecyclerView.ViewHolder(v){
    val imageView : ImageView =
            v.findViewById(R.id.episode_image) as ImageView

    object Factory{
        fun newInstance(viewGroup: ViewGroup) : EpisodeViewHolder {
            val view = LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.episode_card, viewGroup, false)
            return EpisodeViewHolder(view)
        }
    }

    fun onBind(imgLink: String) {
        Glide
                .with(imageView.context)
                .load(imgLink)
                .fitCenter()
                /*.asBitmap()
        .into(object : SimpleTarget<Bitmap>(992, 690) {
            override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                imageView.setImageBitmap(resource)
            }
        })*/
                .into(imageView)
    }
}
