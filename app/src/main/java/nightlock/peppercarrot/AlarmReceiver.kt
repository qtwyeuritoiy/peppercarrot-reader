package nightlock.peppercarrot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import nightlock.peppercarrot.utils.ArchiveDataManager

/**
 * Broadcast Receiver for updating comic list.
 * Created by graphene on 24/08/17.
 */

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Thread {
            context?.let {
                ArchiveDataManager.updateArchive(it)
            }
        }.start()
    }
}