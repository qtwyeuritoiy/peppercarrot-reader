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
package nightlock.peppercarrot.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vlonjatg.progressactivity.ProgressRelativeLayout
import io.github.mthli.sugartask.SugarTask
import nightlock.peppercarrot.R
import nightlock.peppercarrot.adapters.ArchiveAdapter
import nightlock.peppercarrot.utils.ArchiveDataManager
import nightlock.peppercarrot.utils.getCoverImageUrl

/**
 * Fragment holding list of comic episodes
 * Created by Jihoon Kim on 4/30/17.
 */

class ArchiveFragment : Fragment(){
    val adapter = ArchiveAdapter()
    var db: ArchiveDataManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_archive, container, false)
        db = ArchiveDataManager(context.applicationContext)
        init(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val mRecycler = view.findViewById(R.id.archive_recycler) as RecyclerView
        val context = view.context
        mRecycler.layoutManager = LinearLayoutManager(context)
        mRecycler.adapter = adapter
    }

    private fun init(view : View) {
        val progress = view.findViewById(R.id.archive_progress) as ProgressRelativeLayout

        initRecyclerView(view)
        if (db!!.length() < 1)
            initArchive(progress, view)
        else {
            Log.d("crystal_ball", "episodeCount available")
            loaded(false)
        }
    }

    private fun loaded(initial : Boolean) {
        for (episode in db!!.getAllEpisode()) {
            if (initial)
                Glide
                    .with(this)
                        .load(getCoverImageUrl(episode))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .preload()

            adapter.addAndNotify(episode)
        }
    }

    private fun initArchive(progress: ProgressRelativeLayout, view: View) {
        progress.showLoading()

        SugarTask
                .with(this)
                .assign {
                    ArchiveDataManager.updateArchive(context.applicationContext)
                }.finish { _ ->
                    progress.showContent()
                    loaded(true)
                }.broken { e ->
                    onError(progress, view, e)
                }.execute()
    }

    private fun onError(progress: ProgressRelativeLayout, view: View, e: Exception) {
        progress.showError(R.drawable.ic_signal_wifi_off_black_24dp, "Network Error",
                "Connect to network and try again", "Retry", { _ ->
            initArchive(progress, view)
        })
        Log.e("crystal_ball", "Error on initArchive()")
        e.printStackTrace()
    }
}
