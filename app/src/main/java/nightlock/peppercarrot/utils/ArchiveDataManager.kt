package nightlock.peppercarrot.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * SQLite Database Helper Class
 * Created by graphene on 05/06/17.
 */
class ArchiveDataManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "episodeDB.db"
        val DATABASE_VERSION = 1
        val JOB_ID = 1337
        val JOB_PERIODIC_TASK_TAG = "nightlock.peppercarrot.utils.ArchiveDataManager"
        val TABLE_NAME = "episodeEntry"
        val COLUMN_NAME_INDEX = "_id"
        val COLUMN_NAME_NAME = "name"
        val COLUMN_NAME_PAGES = "pages"
        val COLUMN_NAME_LANGUAGES = "languages"

        val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME($COLUMN_NAME_INDEX" +
                " INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME_NAME TEXT," +
                " $COLUMN_NAME_PAGES INTEGER, $COLUMN_NAME_LANGUAGES TEXT)"
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
        val SQL_SELECT_ALL = "SELECT * FROM $TABLE_NAME"

        fun updateArchive(context: Context) {
            for (episode in getEpisodeList()) ArchiveDataManager(context).addEpisode(episode)
        }
    }

    override fun onCreate(db: SQLiteDatabase) = db.execSQL(SQL_CREATE_ENTRIES)

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) =
            onUpgrade(db, oldVersion, newVersion)

    fun addEpisode(episode: Episode) {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_NAME_INDEX, episode.index)
        values.put(COLUMN_NAME_NAME, episode.name)
        values.put(COLUMN_NAME_PAGES, episode.pages)
        values.put(COLUMN_NAME_LANGUAGES, episode.supported_languages.joinToString(separator = ","))

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun length(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery(SQL_SELECT_ALL, null)
        val count = cursor.count

        cursor.close()
        db.close()

        return count
    }

    fun getEpisode(index: Int): Episode {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(
                COLUMN_NAME_INDEX,
                COLUMN_NAME_NAME,
                COLUMN_NAME_PAGES,
                COLUMN_NAME_LANGUAGES
        ), "$COLUMN_NAME_INDEX = ?", arrayOf(index.toString()), null, null, null, null)

        cursor.moveToFirst()

        return getEpisodeFromCursor(cursor)
    }

    private fun getEpisodeFromCursor(cursor: Cursor): Episode {
        val index = cursor.getInt(0)
        val name = cursor.getString(1)
        val pages = cursor.getInt(2)
        val langs = cursor.getString(3).split(",")

        return Episode(index, name, pages, langs)
    }

    fun getAllEpisode(): List<Episode> {
        val episodeList = ArrayList<Episode>()
        val db = readableDatabase
        val cursor = db.rawQuery(SQL_SELECT_ALL, null)

        if (cursor.moveToFirst())
            do episodeList += getEpisodeFromCursor(cursor) while (cursor.moveToNext())

        return episodeList
    }

    fun getTotalPageCount(): Int {
        var pageCount = 0
        val db = readableDatabase
        val cursor = db.rawQuery(SQL_SELECT_ALL, null)

        if (cursor.moveToFirst())
            do pageCount += cursor.getInt(2) while (cursor.moveToNext())

        cursor.close()
        db.close()
        return pageCount
    }
}