package nightlock.peppercarrot.activities

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import nightlock.peppercarrot.R
import nightlock.peppercarrot.fragments.AboutFragment
import nightlock.peppercarrot.fragments.ArchiveFragment
import nightlock.peppercarrot.fragments.PreferenceFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        initToolbar(toolbar)
        checkInit()
    }

    private fun checkInit() {
        val pref = getSharedPreferences("archive", Context.MODE_PRIVATE)
        if (! pref.contains("jobId")){
        }
    }

    private fun initToolbar(toolbar: Toolbar) {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_archive -> swapFragment(ArchiveFragment())
            R.id.nav_settings -> swapFragment(PreferenceFragment())
            R.id.nav_about -> swapFragment(AboutFragment())
        }

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
