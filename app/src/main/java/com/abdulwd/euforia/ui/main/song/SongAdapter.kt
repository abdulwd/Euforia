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

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulwd.euforia.R.layout.item_list_song
import com.abdulwd.euforia.di.scopes.ActivityScope
import com.abdulwd.euforia.models.Song
import kotlinx.android.synthetic.main.item_list_song.view.*
import javax.inject.Inject

/**
 * An adapter for [SongFragment] for providing data.
 */

@ActivityScope
class SongAdapter @Inject constructor()
    : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    lateinit var songList: List<Song>

    override fun getItemCount(): Int = songList.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.song?.text = songList[position].title
        holder?.artist?.text = songList[position].artist
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(item_list_song, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var albumArt = itemView.item_list_song_album_art
        var song = itemView.item_list_song_name
        var artist = itemView.item_list_artist_name
        var popupMenu = itemView.list_item_popup_menu
    }

}