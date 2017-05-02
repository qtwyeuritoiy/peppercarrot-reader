package nightlock.peppercarrot.viewholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nightlock.peppercarrot.R

/**
 * Created by nightlock on 5/2/17.
 */

class EpisodeViewHolder private constructor(v: View): RecyclerView.ViewHolder(v){
    init {

    }

    object Factory{
        fun newInstance(viewGroup: ViewGroup) : EpisodeViewHolder {
            val view = LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.episode_card, viewGroup, false)
            return EpisodeViewHolder(view)

        }

    }

    fun onBind(position: Int) {

    }
}
