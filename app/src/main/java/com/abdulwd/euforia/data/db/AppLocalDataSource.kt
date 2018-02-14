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

import android.database.Cursor
import android.provider.MediaStore
import com.abdulwd.euforia.libraries.sqlbrite.mapToMap
import com.abdulwd.euforia.models.Song
import com.squareup.sqlbrite3.BriteContentResolver
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides local data.
 */

@Singleton
class AppLocalDataSource @Inject constructor(private val mBriteContentResolver: BriteContentResolver) : LocalDataSource {
    override val songs: Observable<List<Song>> = mBriteContentResolver.createQuery(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Song.projection,
            null, null, MediaStore.Audio.Media.TITLE, false)
            .mapToList {
                val id = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                val album = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
                val albumId = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))
                val artist = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                val title = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                val song = Song(id = id,
                        album = album,
                        albumId = albumId,
                        artist = artist,
                        title = title,
                        albumArtPath = albumIdArtMap[albumId])
                song
            }

    private val albumIdArtMap: Map<String, String?> by lazy {
        mBriteContentResolver.createQuery(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART),
                null,
                null,
                null,
                false)
                .mapToMap(Function<Cursor, Map.Entry<String, String?>> {
                    return@Function object : Map.Entry<String, String?> {
                        override val key: String = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID))
                        override val value: String? = it.getString(it.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART))
                    }
                }).blockingFirst()
    }
}