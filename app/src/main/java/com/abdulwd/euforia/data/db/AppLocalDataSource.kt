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

package com.abdulwd.euforia.data.db

import android.provider.MediaStore
import com.abdulwd.euforia.models.Song
import com.squareup.sqlbrite2.BriteContentResolver
import io.reactivex.Observable
import org.jetbrains.anko.info
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides local data.
 */

@Singleton
class AppLocalDataSource @Inject constructor(mBriteContentResolver: BriteContentResolver) : LocalDataSource {
    override val songsList: Observable<List<Song>> = mBriteContentResolver.createQuery(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Song.projection,
            null, null, null, false)
            .mapToList { cursor ->
                val song = Song(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)))
                info { song }
                song
            }
}