package com.wcisang.core.domain.model
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items (

	@Json(name="resourceURI") val resourceURI : String,
	@Json(name="name") val name : String
): Parcelable