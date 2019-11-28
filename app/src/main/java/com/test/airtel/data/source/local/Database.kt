package com.test.airtel.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.airtel.model.AddressListItem


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */

@Database(entities = arrayOf(AddressListItem::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun addressDao(): AddressDao
}