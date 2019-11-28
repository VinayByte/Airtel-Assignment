package com.test.airtel

import android.app.Activity
import android.app.Application
import com.test.airtel.dagger.component.DaggerAppComponent
import com.test.airtel.dagger.modules.AppModule
import com.test.airtel.dagger.modules.ServiceGeneratorModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
class App: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .serviceGeneratorModule(ServiceGeneratorModule(BuildConfig.URL))
            .build().inject(this)    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}