/*
 * Copyright (c) 2018 Abdul Wadood
 *
 * Licensed under the GNU General Public License v3
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.abdulwd.euforia.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.abdulwd.euforia.R
import com.abdulwd.euforia.ui.base.BaseActivity
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.info
import javax.inject.Inject

class MainActivity : BaseActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        MainContract.View {

    private val mPermissionWriteExternalStorage = 1

    @Inject
    lateinit var mLazyMainPagerAdapter: Lazy<MainPagerAdapter>

    @Inject
    lateinit var mPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            showTabs()
            info { "Has storage permissions" }
        } else {
            info { "Does not have storage permissions" }
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    mPermissionWriteExternalStorage)
        }

    }

    override fun onResume() {
        super.onResume()
        mPresenter.bindView(this)
    }

    override fun onPause() {
        mPresenter.unbindView()
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            mPermissionWriteExternalStorage -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    showTabs()
                } else {
                    showSnackbar()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            mPermissionWriteExternalStorage -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showTabs()
                } else {
                    showSnackbar()
                }
            }
        }
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(mainViewPager.rootView, getString(R.string.permission_message), Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(getString(R.string.grant), {
            val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", packageName, null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivityForResult(intent, mPermissionWriteExternalStorage)
        })
        snackbar.show()
    }

    private fun showTabs() {
        mainViewPager.adapter = mLazyMainPagerAdapter.get()
        mainTabLayout.setupWithViewPager(mainViewPager)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_library -> {
                // Handle the camera action
            }
            R.id.nav_playlists -> {

            }
            R.id.nav_queue -> {

            }
            R.id.nav_folders -> {

            }
            R.id.nav_settings -> {

            }
            R.id.nav_help_and_feedback -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_options_menu, menu)
        return true
    }
}
