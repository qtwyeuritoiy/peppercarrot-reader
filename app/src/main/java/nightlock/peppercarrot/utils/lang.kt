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
package nightlock.peppercarrot.utils

import android.util.Log
import java.util.*

/**
 * Utility functions for multi-language support.
 * Created by Jihoon Kim on 20/07/17.
 */
fun getPreferredLanguage(available_languages: List<String>): String {

    var locale:Locale = Locale.getDefault()

    //if device default locale is not available return english
    if(available_languages.none { Locale(it).language.equals(locale.language) } ){
        return Locale.US.language
    }
    else{
        return locale.language
    }


}