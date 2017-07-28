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
package nightlock.peppercarrot.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.utils.Episode
import nightlock.peppercarrot.viewholders.EpisodeViewHolder

/**
 * Adapter for Displaying EpisodeViewHolder
 * Created by Jihoon Kim on 5/2/17.
 */

class ArchiveAdapter: RecyclerView.Adapter<EpisodeViewHolder>() {
    val episodeList = ArrayList<Episode>()

    override fun getItemCount() = episodeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            EpisodeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) =
            holder.onBind(episodeList[position])

    fun addAndNotify(episode: Episode) {
        episodeList.add(episode)
        notifyItemInserted(itemCount-1)
    }

}
