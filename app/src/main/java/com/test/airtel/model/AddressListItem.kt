package com.test.airtel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
	tableName = "address"
)
data class AddressListItem(

	@Json(name="id")
	@PrimaryKey
	@ColumnInfo(name = "id")
	val id: String,

	@Json(name="city")
	@ColumnInfo(name = "city")
	val city: String? = null,

	@Json(name="pinCode")
	@ColumnInfo(name = "pinCode")
	val pinCode: String? = null,

	@Json(name="addressString")
	@ColumnInfo(name = "addressString")
	val addressString: String? = null



)