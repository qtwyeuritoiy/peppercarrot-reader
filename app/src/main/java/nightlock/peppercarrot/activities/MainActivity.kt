package nightlock.peppercarrot.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import io.hypertrack.smart_scheduler.Job
import io.hypertrack.smart_scheduler.SmartScheduler
import nightlock.peppercarrot.R
import nightlock.peppercarrot.fragments.AboutFragment
import nightlock.peppercarrot.fragments.ArchiveFragment
import nightlock.peppercarrot.fragments.PreferenceFragment
import nightlock.peppercarrot.utils.ArchiveDataManager

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val fragmentList = HashMap<Int, Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        initToolbar(toolbar)
        checkAndInit()
    }

    private fun checkAndInit() {
        val jobScheduler = SmartScheduler.getInstance(applicationContext)
        if (jobScheduler.contains(ArchiveDataManager.JOB_ID)) {
            val jobBuilder = Job.Builder(ArchiveDataManager.JOB_ID, { context, job ->
                if (job != null) ArchiveDataManager.updateArchive(context)
            }, ArchiveDataManager.JOB_PERIODIC_TASK_TAG)
                    .setRequiredNetworkType(Job.NetworkType.NETWORK_TYPE_UNMETERED)
                    .setRequiresCharging(false)
                    .setPeriodic(1000 * 60 * 60 * 8) //Since P&C update is out on nearly monthly basis, we don't need to check most of the time.
                    .setFlex(1000 * 60 * 60 * 12)

            val job = jobBuilder.build()
            jobScheduler.addJob(job)
        }

        //Put Fragments into the list and load ArchiveFragment on FrameLayout
        fragmentList.put(R.id.nav_archive, ArchiveFragment())
        fragmentList.put(R.id.nav_settings, PreferenceFragment())
        fragmentList.put(R.id.nav_about, AboutFragment())
        swapFragment(fragmentList[R.id.nav_archive]!!)
    }

    private fun initToolbar(toolbar: Toolbar) {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout

        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        swapFragment(fragmentList[item.itemId]!!)

        // Closes drawer.
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, fragment)
                .commit()
    }
}
