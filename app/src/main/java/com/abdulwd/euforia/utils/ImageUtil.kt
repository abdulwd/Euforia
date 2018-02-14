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

package com.abdulwd.euforia.utils

import android.widget.ImageView
import com.abdulwd.euforia.R
import com.squareup.picasso.Picasso
import java.io.File

/**
 * Contains function for image loading.
 */

object ImageUtil {
    fun loadImage(imageView: ImageView?, path: String?) {
        if (path != null) {
            Picasso.with(imageView?.context)
                    .load(File(path))
                    .placeholder(R.drawable.side_nav_bar)
                    .into(imageView)
        } else {
            Picasso.with(imageView?.context)
                    .load(R.mipmap.ic_launcher_round)
                    .into(imageView)
        }
    }
}