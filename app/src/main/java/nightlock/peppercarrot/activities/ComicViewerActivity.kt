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

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager
import nightlock.peppercarrot.R
import nightlock.peppercarrot.adapters.ComicViewerAdapter


class ComicViewerActivity : AppCompatActivity() {
    private val hideHandler = Handler()

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {
        contentView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        val actionBar = supportActionBar
        actionBar?.show()
        controlsView.visibility = View.VISIBLE
    }
    private var visibility: Boolean = false
    private val hideRunnable = Runnable { hide() }

    private val delayHideTouch = {
        if (AUTO_HIDE) delayedHide(AUTO_HIDE_DELAY_MILLIS)
        false
    }

    lateinit private var controlsView: View
    lateinit private var comicViewerAdapter: ComicViewerAdapter
    lateinit private var rootFrame: FrameLayout

    lateinit private var contentView: RecyclerViewPager

    lateinit private var backButton: ImageButton
    lateinit private var forwardButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_comic_viewer)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        visibility = true
        controlsView = findViewById(R.id.fullscreen_content_controls)
        // Set up the RecyclerViewPager with the ComicViewerAdapter.
        contentView = findViewById(R.id.comic_viewpager) as RecyclerViewPager
        rootFrame = findViewById(R.id.comic_viewer_frame) as FrameLayout

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        backButton = findViewById(R.id.button_back) as ImageButton
        forwardButton = findViewById(R.id.button_forward) as ImageButton

        backButton.setOnTouchListener { _, _ -> delayHideTouch() }
        forwardButton.setOnTouchListener { _, _ -> delayHideTouch() }

        val index = intent.getIntExtra("ep", 0)

        comicViewerAdapter = ComicViewerAdapter(applicationContext, index)

        comicViewerAdapter.notifyDataSetChanged()
        contentView.adapter = comicViewerAdapter
        contentView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        BigImageViewer.initialize(GlideImageLoader.with(applicationContext))
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delayedHide(100)
    }

    override fun onResume() {
        super.onResume()
        delayedHide(100)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun toggle() = if (visibility) hide() else show()

    private fun hide() {
        val actionBar = supportActionBar
        actionBar?.hide()
        controlsView.visibility = View.GONE
        visibility = false

        hideHandler.removeCallbacks(mShowPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @SuppressLint("InlinedApi")
    private fun show() {
        contentView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        visibility = true
        Log.d("crystal_ball", "show()")

        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        private val AUTO_HIDE = true
        private val AUTO_HIDE_DELAY_MILLIS = 3000
        private val UI_ANIMATION_DELAY = 300
    }
}
