package com.test.airtel.dagger.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.test.airtel.constants.Constants
import com.test.airtel.data.source.local.AddressDao
import com.test.airtel.data.source.local.Database
import com.test.airtel.utils.Utils
import com.test.airtel.viewmodel.AddressViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
@Module
class AppModule(val app: Application) {

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE address RENAME TO address")
            }
        }
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideAddressDatabase(app: Application): Database = Room.databaseBuilder(app,
        Database::class.java, Constants.DATABASE_NAME)
        /*.addMigrations(MIGRATION_1_2)*/
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideAddressDao(
        database: Database): AddressDao = database.addressDao()

    @Provides
    @Singleton
    fun provideAddressViewModelFactory(
        factory: AddressViewModelFactory
    ): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)

}