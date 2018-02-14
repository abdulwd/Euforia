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

package com.abdulwd.euforia.data

import android.content.Context
import com.abdulwd.euforia.data.db.AppLocalDataSource
import com.abdulwd.euforia.data.db.LocalDataSource
import com.squareup.sqlbrite3.BriteContentResolver
import com.squareup.sqlbrite3.SqlBrite
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Module for injection in data package.
 */

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideSqlBrite(): SqlBrite = SqlBrite.Builder().build()

    @Provides
    @Singleton
    fun provideBriteContentResolver(context: Context, sqlBrite: SqlBrite): BriteContentResolver =
            sqlBrite.wrapContentProvider(context.contentResolver, Schedulers.io())

    @Provides
    @Singleton
    fun provideLocalDataSource(appLocalDataSource: AppLocalDataSource): LocalDataSource =
            appLocalDataSource
}