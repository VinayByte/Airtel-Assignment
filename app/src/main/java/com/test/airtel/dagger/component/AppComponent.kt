package com.test.airtel.dagger.component

import com.test.airtel.App
import com.test.airtel.dagger.modules.AppModule
import com.test.airtel.dagger.modules.BuildersModule
import com.test.airtel.dagger.modules.ServiceGeneratorModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class, ServiceGeneratorModule::class)
)
interface AppComponent {
    fun inject(app: App)
}