package com.test.airtel.model

import com.squareup.moshi.Json

data class Response(

	@Json(name="data")
	val data: Data? = null,

	@Json(name="requestId")
	val requestId: String? = null
)