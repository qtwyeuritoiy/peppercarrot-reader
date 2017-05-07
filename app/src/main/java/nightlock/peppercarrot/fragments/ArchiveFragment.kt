package nightlock.peppercarrot.fragments

import android.content.Context
import android.content.SharedPreferences
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
import nightlock.peppercarrot.utils.getAvailablePreferredLocale
import nightlock.peppercarrot.utils.getEpisodeList

/**
 * Created by nightlock on 4/30/17.
 */

class ArchiveFragment : Fragment(){
    val adapter = ArchiveAdapter()
    var pref : SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.content_archive, container, false)
        pref = context.getSharedPreferences("archive", Context.MODE_PRIVATE)
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
        if (! pref!!.contains("episodeCount")) {
            initArchive(progress, view)
        } else {
            Log.d("crystal_ball", "episodeCount available")
            loaded(false)
        }
    }

    private fun loaded(initial : Boolean) {
        for (i in 1..pref!!.getInt("episodeCount", 0)) {
            val episode = pref!!.getString("ep$i", "")
            val locale = pref!!.getString("locale$i", "en")
            val link = "https://www.peppercarrot.com/0_sources/$episode/low-res/$locale" +
                    "_Pepper-and-Carrot_by-David-Revoy_E${if (i<10) "0" else ""}$i.jpg"

            if (initial)
                Glide
                    .with(this)
                    .load(link)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .preload()

            adapter.addAndNotify(link)
        }
    }

    private fun initArchive(progress: ProgressRelativeLayout, view: View) {
        progress.showLoading()

        SugarTask
                .with(this)
                .assign { ->
                    val episodeList = getEpisodeList() //as List<String>
                    val writer = pref!!.edit()
                    writer.putInt("episodeCount", episodeList.size)

                    for (i in episodeList.indices) {
                        val episode = episodeList[i]
                        val count = i+1
                        val locale = getAvailablePreferredLocale(episode)

                        writer.putString("ep$count", episode)
                        writer.putString("locale$count", locale)
                    }
                    writer.commit() //Should be blocked to ensure all of the content is added before invoking loaded()
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
        Log.e("crystal_ball", "Error on initArchive(): ${e.message}")
    }
}
