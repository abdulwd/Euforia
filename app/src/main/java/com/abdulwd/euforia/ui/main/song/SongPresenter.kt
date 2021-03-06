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

import com.abdulwd.euforia.data.AppDataManager
import com.abdulwd.euforia.di.scopes.ActivityScope
import com.abdulwd.euforia.models.Song
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import javax.inject.Inject

/**
 * Listens to user actions from the UI [SongFragment], retrieves the data and updates
 * the UI as required.
 */

@ActivityScope
class SongPresenter @Inject constructor() : SongContract.Presenter {
    override var mView: SongContract.View? = null

    @Inject
    lateinit var mAppDataManager: AppDataManager

    @Inject
    lateinit var mLazySongAdapter: SongAdapter

    override fun setAdapterData(songs: List<Song>) {
        mLazySongAdapter.songs = songs
    }

    override fun loadSongs() {
        mAppDataManager.songs.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setAdapterData(it)
                    mView?.recyclerView?.adapter = mLazySongAdapter
                }, { error { it } }, { info { "Song list loaded" } })
    }
}