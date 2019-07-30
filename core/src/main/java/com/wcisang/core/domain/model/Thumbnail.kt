package com.wcisang.core.domain.model
import com.squareup.moshi.Json

data class Thumbnail (

	@Json(name="path") val path : String,
	@Json(name="extension") val extension : String
)