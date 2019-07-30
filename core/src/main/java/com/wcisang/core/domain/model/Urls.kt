package com.wcisang.core.domain.model
import com.squareup.moshi.Json

data class Urls (

	@Json(name="type") val type : String,
	@Json(name="url") val url : String
)