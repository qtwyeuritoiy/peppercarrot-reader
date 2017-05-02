package nightlock.peppercarrot.fragments

import android.os.Bundle

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nightlock.peppercarrot.R
import nightlock.peppercarrot.adapters.ArchiveAdapter

/**
 * Created by nightlock on 4/30/17.
 */

class ArchiveFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.content_archive, container, false)

        val mRecycler = view.findViewById(R.id.archive_recycler) as RecyclerView
        mRecycler.layoutManager = LinearLayoutManager(view.context)
        mRecycler.adapter = ArchiveAdapter()

        return view
    }
}