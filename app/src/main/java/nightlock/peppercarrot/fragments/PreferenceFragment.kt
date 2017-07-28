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
