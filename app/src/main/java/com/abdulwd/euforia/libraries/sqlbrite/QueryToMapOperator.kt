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
import com.squareup.sqlbrite3.SqlBrite
import io.reactivex.ObservableOperator
import io.reactivex.Observer
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.plugins.RxJavaPlugins

/**
 * Custom QueryToMapOperator.
 */

class QueryToMapOperator<K, V>(private val mapper: Function<Cursor, Map.Entry<K, V>>) : ObservableOperator<Map<K, V>, SqlBrite.Query> {

    override fun apply(observer: Observer<in Map<K, V>>): Observer<in SqlBrite.Query> {
        return MappingObserver(observer, mapper)
    }

    internal class MappingObserver<K, V>(private val downstream: Observer<in Map<K, V>>,
                                         private val mapper: Function<Cursor, Map.Entry<K, V>>)
        : DisposableObserver<SqlBrite.Query>() {

        override fun onStart() {
            downstream.onSubscribe(this)
        }

        override fun onNext(query: SqlBrite.Query) {
            try {
                val cursor = query.run()
                if (cursor == null || isDisposed) {
                    return
                }
                val map: MutableMap<K, V> = HashMap(cursor.count)
                cursor.use {
                    while (it.moveToNext()) {
                        val entry = mapper.apply(it)
                        map[entry.key] = entry.value
                    }
                }
                if (!isDisposed) {
                    downstream.onNext(map)
                }
            } catch (e: Throwable) {
                Exceptions.throwIfFatal(e)
                onError(e)
            }

        }

        override fun onComplete() {
            if (!isDisposed) {
                downstream.onComplete()
            }
        }

        override fun onError(e: Throwable) {
            if (isDisposed) {
                RxJavaPlugins.onError(e)
            } else {
                downstream.onError(e)
            }
        }
    }
}
