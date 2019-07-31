package com.wcisang.core.domain.model
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail (

	@Json(name="path") val path : String,
	@Json(name="extension") val extension : String
) : Parcelable