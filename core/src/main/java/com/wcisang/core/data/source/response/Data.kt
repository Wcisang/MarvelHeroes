package com.wcisang.core.data.source.response
import com.squareup.moshi.Json
import com.wcisang.core.domain.model.Character

data class Data (

	@Json(name="offset") val offset : Int,
	@Json(name="limit") val limit : Int,
	@Json(name="total") val total : Int,
	@Json(name="count") val count : Int,
	@Json(name="results") val results : List<Character>
)