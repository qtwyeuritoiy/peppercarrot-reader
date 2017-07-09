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

/**
 * Fragment holding list of comic episodes
 * Created by nightlock on 4/30/17.
 */

class ArchiveFragment : Fragment(){
    val adapter = ArchiveAdapter()
    var db: ArchiveDataManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.content_archive, container, false)
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
        if (db!!.length() < 1) {
            initArchive(progress, view)
        } else {
            Log.d("crystal_ball", "episodeCount available")
            loaded(false)
        }
    }

    private fun loaded(initial : Boolean) {
        for (episode in db!!.getAllEpisode()) {
            val name = episode.name
            val index = episode.index + 1
            val locale = "en" //Temporary workaround; should be omitted upon release.
            val link = "https://www.peppercarrot.com/0_sources/$name/low-res/$locale" +
                    "_Pepper-and-Carrot_by-David-Revoy_E${if (index < 10) "0" else ""}$index.jpg"

            if (initial)
                Glide
                    .with(this)
                    .load(link)
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
