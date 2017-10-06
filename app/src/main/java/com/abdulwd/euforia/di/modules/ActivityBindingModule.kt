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

package com.abdulwd.euforia.di.modules

import com.abdulwd.euforia.di.scopes.ActivityScope
import com.abdulwd.euforia.ui.main.MainActivity
import com.abdulwd.euforia.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger.Android annotation processor will create the sub-components. We also specify the modules
 * to be used by each sub-components and make Dagger.Android aware of a scope annotation
 * ([ActivityScope]).
 */

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun mainActivity(): MainActivity
}