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

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.piasy.biv.view.BigImageView
import nightlock.peppercarrot.R

/**
 * ViewHolder that has a single ImageView for showing a comic page.
 * Created by Jihoon Kim on 5/7/17.
 */
class ComicViewHolder private constructor(v: View): RecyclerView.ViewHolder(v){
    val imageView : BigImageView =
            v.findViewById(R.id.comic_image) as BigImageView

    fun onBind(imgLink: String) = imageView.showImage(Uri.parse(imgLink))

    companion object {
        fun newInstance(viewGroup: ViewGroup) : ComicViewHolder {
            val view = LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.fragment_comic_viewer, viewGroup, false)
            return ComicViewHolder(view)
        }
    }
}