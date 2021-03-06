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

package com.abdulwd.euforia.ui.main.song

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulwd.euforia.di.scopes.ActivityScope
import com.abdulwd.euforia.ui.base.BaseFragment
import javax.inject.Inject

/**
 * This fragment displays the list of songs.
 */

@ActivityScope
class SongFragment @Inject constructor() : BaseFragment(), SongContract.View {

    override lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var mPresenter: SongPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recyclerView = RecyclerView(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return recyclerView
    }

    override fun onResume() {
        super.onResume()
        mPresenter.bindView(this)
        mPresenter.loadSongs()
    }

    override fun onPause() {
        mPresenter.unbindView()
        super.onPause()
    }
}