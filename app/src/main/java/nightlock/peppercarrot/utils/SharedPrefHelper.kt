package nightlock.peppercarrot.utils

import android.content.Context

/**
 * Created by qtwye on 2017-05-03.
 */

val ARCHIVE = "archiveinfo"

fun writeInt(context: Context, key: String, value: Int) {
    val pref = context.getSharedPreferences(ARCHIVE, Context.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putInt(key, value)
    editor.apply()
}

fun writeStr(context: Context, key: String, value: String) {
    val pref = context.getSharedPreferences(ARCHIVE, Context.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putString(key, value)
    editor.apply()
}