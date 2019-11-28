package com.test.airtel.dagger.modules

import com.test.airtel.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}