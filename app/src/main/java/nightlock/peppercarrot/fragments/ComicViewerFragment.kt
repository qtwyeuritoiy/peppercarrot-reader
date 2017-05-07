package nightlock.peppercarrot.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nightlock.peppercarrot.R

/**
 * Created by nightlock on 5/7/17.
 */

class ComicViewerFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_comic_viewer, container, false)

        return view
    }
}