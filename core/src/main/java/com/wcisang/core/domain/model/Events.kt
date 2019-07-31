package com.wcisang.core.domain.model
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events (

	@Json(name="available") val available : Int,
	@Json(name="collectionURI") val collectionURI : String,
	@Json(name="items") val items : List<Items>,
	@Json(name="returned") val returned : Int
): Parcelable