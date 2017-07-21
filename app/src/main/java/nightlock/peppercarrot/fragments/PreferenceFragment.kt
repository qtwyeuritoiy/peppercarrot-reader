package nightlock.peppercarrot.fragments

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import nightlock.peppercarrot.R

/**
 * Fragment for Settings
 * Created by Jihoon Kim on 4/30/17.
 */

class PreferenceFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Load the preferences from an XML resource
        //Handler().postDelayed({addPreferencesFromResource(R.xml.preferences)}, 250)
        addPreferencesFromResource(R.xml.preferences)
    }

}
