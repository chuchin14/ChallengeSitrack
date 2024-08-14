package com.example.challenge.sitrack.feature.main.data.models

import com.example.challenge.sitrack.feature.main.domain.models.RandomUserModel
import com.google.gson.annotations.SerializedName

data class GetRandomUserApiResponse(
    @SerializedName("results") var results: ArrayList<Results>?,
    @SerializedName("info") var info: Info?
)

fun GetRandomUserApiResponse.toRandomUserModel(): RandomUserModel {
    val gender = this.results?.first()?.gender
    val title = this.results?.first()?.name?.title
    val name = this.results?.first()?.name?.first
    val lastName = this.results?.first()?.name?.last
    val streetName = this.results?.first()?.location?.street?.name
    val streetNumber = this.results?.first()?.location?.street?.number
    val city = this.results?.first()?.location?.city
    val state = this.results?.first()?.location?.state
    val country = this.results?.first()?.location?.country
    val postCode = this.results?.first()?.location?.postcode
    val pictureLarge = this.results?.first()?.picture?.large
    val latitude = this.results?.first()?.location?.coordinates?.latitude
    val longitude = this.results?.first()?.location?.coordinates?.longitude
    val email = this.results?.first()?.email
    val randomUserModel = RandomUserModel(
        title = title.orEmpty(),
        name = name.orEmpty(),
        lastname = lastName.orEmpty(),
        gender = gender.orEmpty(),
        streetName = streetName.orEmpty(),
        streetNumber = streetNumber.orDefault(),
        city = city.orEmpty(),
        state = state.orEmpty(),
        country = country.orEmpty(),
        postCode = postCode.orEmpty(),
        pictureLarge = pictureLarge.orEmpty(),
        latitude = latitude?.toDouble().orDefault(),
        longitude = longitude?.toDouble().orDefault(),
        email = email.orEmpty()
    )
    return randomUserModel
}

fun String?.orEmpty() = this ?: ""
fun Int?.orDefault() = this ?: 0
fun Double?.orDefault() = this ?: 0.0
/*fun String?.orEmpty(): String {
    return if (this == null) "" else this
}*/

data class Results(
    @SerializedName("gender") var gender: String?,
    @SerializedName("name") var name: Name?,
    @SerializedName("location") var location: Location?,
    @SerializedName("email") var email: String?,
    @SerializedName("login") var login: Login?,
    @SerializedName("dob") var dob: Dob?,
    @SerializedName("registered") var registered: Registered?,
    @SerializedName("phone") var phone: String?,
    @SerializedName("cell") var cell: String?,
    @SerializedName("id") var id: Id?,
    @SerializedName("picture") var picture: Picture?,
    @SerializedName("nat") var nat: String?
)

data class Name(
    @SerializedName("title") var title: String?,
    @SerializedName("first") var first: String?,
    @SerializedName("last") var last: String?
)

data class Location(
    @SerializedName("street") var street: Street?,
    @SerializedName("city") var city: String?,
    @SerializedName("state") var state: String?,
    @SerializedName("country") var country: String?,
    @SerializedName("postcode") var postcode: String?,
    @SerializedName("coordinates") var coordinates: Coordinates?,
    @SerializedName("timezone") var timezone: Timezone?
)

data class Street(
    @SerializedName("number") var number: Int?,
    @SerializedName("name") var name: String?
)

data class Coordinates(
    @SerializedName("latitude") var latitude: String?,
    @SerializedName("longitude") var longitude: String?
)

data class Timezone(
    @SerializedName("offset") var offset: String?,
    @SerializedName("description") var description: String?
)

data class Login(
    @SerializedName("uuid") var uuid: String?,
    @SerializedName("username") var username: String?,
    @SerializedName("password") var password: String?,
    @SerializedName("salt") var salt: String?,
    @SerializedName("md5") var md5: String?,
    @SerializedName("sha1") var sha1: String?,
    @SerializedName("sha256") var sha256: String?
)

data class Dob(
    @SerializedName("date") var date: String?,
    @SerializedName("age") var age: Int?
)

data class Registered(
    @SerializedName("date") var date: String?,
    @SerializedName("age") var age: Int?
)

data class Id(
    @SerializedName("name") var name: String?,
    @SerializedName("value") var value: String?
)

data class Picture(
    @SerializedName("large") var large: String?,
    @SerializedName("medium") var medium: String?,
    @SerializedName("thumbnail") var thumbnail: String?
)

data class Info(
    @SerializedName("seed") var seed: String?,
    @SerializedName("results") var results: Int?,
    @SerializedName("page") var page: Int?,
    @SerializedName("version") var version: String?
)