package com.wcisang.core.domain.model
import com.squareup.moshi.Json

data class Series (

	@Json(name="available") val available : Int,
	@Json(name="collectionURI") val collectionURI : String,
	@Json(name="items") val items : List<Items>,
	@Json(name="returned") val returned : Int
)