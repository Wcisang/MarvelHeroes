package com.wcisang.core.domain.model
import com.squareup.moshi.Json

data class Items (

	@Json(name="resourceURI") val resourceURI : String,
	@Json(name="name") val name : String
)