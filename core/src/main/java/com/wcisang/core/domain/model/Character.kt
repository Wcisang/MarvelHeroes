package com.wcisang.core.domain.model
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character (

	@Json(name="id") val id : Int,
	@Json(name="name") val name : String,
	@Json(name="description") val description : String,
	@Json(name="modified") val modified : String,
	@Json(name="thumbnail") val thumbnail : Thumbnail,
	@Json(name="resourceURI") val resourceURI : String,
	@Json(name="comics") val comics : Comics,
	@Json(name="series") val series : Series,
	@Json(name="stories") val stories : Stories,
	@Json(name="events") val events : Events,
	@Json(name="urls") val urls : List<Urls>
) : Parcelable