/*
 * Copyright (C) 2017 Jihoon Kim <imsesaok@gmail.com, imsesaok@tuta.io>
 *
 * This file is part of Reader for Pepper&Carrot.
 *
 * Reader for Pepper&Carrot is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import nightlock.peppercarrot.utils.getPreferredLanguage
import java.util.*

/**
 * ViewHolder for containing the episode cover.
 * Created by Jihoon Kim on 5/2/17.
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
        val locale = getPreferredLanguage(episode.supported_languages)
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
