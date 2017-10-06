/*
 * Copyright (c) 2017 Abdul Wadood
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

package com.abdulwd.euforia.di.components

import com.abdulwd.euforia.EuforiaApp
import com.abdulwd.euforia.di.modules.ActivityBindingModule
import com.abdulwd.euforia.di.modules.AppModule
import com.abdulwd.euforia.ui.base.BaseActivity
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Component used by dagger for injection in classes.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent {
    fun inject(euforiaApp: EuforiaApp)
    fun inject(baseActivity: BaseActivity)
}