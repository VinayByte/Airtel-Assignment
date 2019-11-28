package com.test.airtel.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.test.airtel.model.AddressListItem


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
@Dao
interface AddressDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(addressListItem : AddressListItem)



//    @Query("SELECT * FROM address")
//    fun queryAddressList(): Single<List<AddressListItem>>



}