/*
 * Copyright (c) 2017 Abdul Wadood
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

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.abdulwd.euforia.R.array.tab_home
import com.abdulwd.euforia.di.scopes.ActivityScope
import com.abdulwd.euforia.ui.main.song.SongFragment
import javax.inject.Inject

/**
 * A pager adapter to provide fragments for lists of songs, albums, artists and genres.
 */

@ActivityScope
class MainPagerAdapter @Inject constructor(activity: MainActivity,
                                           private var mSongFragment: SongFragment) :
        FragmentStatePagerAdapter(activity.supportFragmentManager) {

    private val tabHomeTitle = activity.resources.getStringArray(tab_home)

    override fun getCount(): Int = 1

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> mSongFragment
        else -> mSongFragment
    }

    override fun getPageTitle(position: Int): CharSequence = tabHomeTitle[position]

}