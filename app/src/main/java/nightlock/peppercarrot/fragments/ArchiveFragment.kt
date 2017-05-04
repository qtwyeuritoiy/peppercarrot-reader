package nightlock.peppercarrot.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlonjatg.progressactivity.ProgressRelativeLayout
import io.github.mthli.sugartask.SugarTask
import nightlock.peppercarrot.R
import nightlock.peppercarrot.adapters.ArchiveAdapter
import nightlock.peppercarrot.utils.getEpisodeList

/**
 * Created by nightlock on 4/30/17.
 */

class ArchiveFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.content_archive, container, false)
        init(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val mRecycler = view.findViewById(R.id.archive_recycler) as RecyclerView
        mRecycler.layoutManager = LinearLayoutManager(view.context)
        mRecycler.adapter = ArchiveAdapter(view.context)
    }

    private fun init(view : View) {
        val progress = view.findViewById(R.id.archive_progress) as ProgressRelativeLayout

        val pref = view.context.getSharedPreferences("archive", Context.MODE_PRIVATE)
        if (! pref.contains("episodeCount"))
            initArchive(progress, pref, view)
        else
            initRecyclerView(view)
    }

    private fun initArchive(progress: ProgressRelativeLayout, pref: SharedPreferences, view: View) {
        progress.showLoading()
        SugarTask
                .with(this)
                .assign { ->
                    val episodeList = getEpisodeList()
                    val writer = pref.edit()
                    writer.putInt("episodeCount", episodeList.size)

                    for (i in episodeList.indices) {
                        val episodeUrl = episodeList[i]
                        writer.putString("ep$i", episodeUrl)
                    }
                    writer.commit()
                }.finish { _ ->
                    progress.showContent()
                    initRecyclerView(view)
                }.broken { e ->
                    progress.showError(R.drawable.ic_signal_wifi_off_black_24dp, "Network Error", "Connect to network and try again", "Retry", { _ -> initArchive(progress, pref, view)})
                }.execute()
    }
}