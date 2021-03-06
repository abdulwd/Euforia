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

package com.abdulwd.euforia.ui.base

import org.jetbrains.anko.AnkoLogger
/**
 * Base contract for holding base view and base presenter.
 */

interface BaseContract {

    /**
     * Base view interface that other view interfaces must inherit.
     */
    interface View<T> : AnkoLogger

    /**
     * Base presenter interface that other presenter interfaces must inherit.
     */
    interface Presenter<T> : AnkoLogger {
        var mView: T?
        /**
         * Binds presenter with a view when resumed. The Presenter will perform initialization here.
         *
         * @param view the view associated with this presenter
         */
        fun bindView(view: T) {
            mView = view
        }

        /**
         * Drops the reference to the view when destroyed
         */
        fun unbindView() {
            mView = null
        }

    }
}