package com.test.airtel.model

import com.squareup.moshi.Json

data class Data(

	@Json(name="addressList")
	val addressList: List<AddressListItem?>? = null,

	@Json(name="autoCompleteRequestString")
	val autoCompleteRequestString: String? = null
)