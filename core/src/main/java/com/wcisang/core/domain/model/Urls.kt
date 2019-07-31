package com.wcisang.core.domain.model
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Urls (

	@Json(name="type") val type : String,
	@Json(name="url") val url : String
): Parcelable