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

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import nightlock.peppercarrot.utils.ArchiveDataManager
import nightlock.peppercarrot.utils.getComicImageUrl
import nightlock.peppercarrot.viewholders.ComicViewHolder

/**
 * Adapter for Displaying ComicViewHolder
 * Created by Jihoon Kim on 5/7/17.
 */

class ComicViewerAdapter(val context: Context, var index: Int) :
        RecyclerView.Adapter<ComicViewHolder>() {
    val db = ArchiveDataManager(context)

    override fun onBindViewHolder(holder: ComicViewHolder?, delta: Int) {
        val episode = db.getEpisode(index)
        holder?.onBind(getComicImageUrl(episode, delta))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder =
            ComicViewHolder.newInstance(parent!!)

    override fun getItemCount() = db.getEpisode(index).pages
}