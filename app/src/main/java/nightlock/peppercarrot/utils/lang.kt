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