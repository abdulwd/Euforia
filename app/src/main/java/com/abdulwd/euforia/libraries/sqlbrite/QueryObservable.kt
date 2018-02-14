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

package com.abdulwd.euforia.libraries.sqlbrite

import android.database.Cursor
import android.support.annotation.CheckResult
import com.squareup.sqlbrite3.QueryObservable
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Custom extension functions for SQLBrite
 */

@CheckResult
fun <K, V> QueryObservable.mapToMap(mapper: Function<Cursor, Map.Entry<K, V>>): Observable<Map<K, V>> {
    return lift(QueryToMapOperator(mapper))
}
