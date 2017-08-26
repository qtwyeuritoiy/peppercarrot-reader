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
package nightlock.peppercarrot.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.mikepenz.aboutlibraries.LibsBuilder
import nightlock.peppercarrot.AlarmReceiver
import nightlock.peppercarrot.R
import nightlock.peppercarrot.fragments.ArchiveFragment


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
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pending = PendingIntent.getBroadcast(applicationContext, 0, intent, 0)

        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_HALF_DAY, pending)

        //Put Fragments into the list and load ArchiveFragment on FrameLayout
        fragmentList.put(R.id.nav_archive, ArchiveFragment())
        //fragmentList.put(R.id.nav_settings, PreferenceFragment())
        fragmentList.put(R.id.nav_about, LibsBuilder()
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                .withAboutDescription("An Unofficial Reader App for Pepper & Carrot, Comic Created with FLOSS.")
                .supportFragment())

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
