package com.artemissoftware.newsroom.navigation.navtypes

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.artemissoftware.newsroom.core.model.Article
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

class ArticleNavType : NavType<GalleryUI>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): GalleryUI? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): GalleryUI {
        return Gson().fromJson(value, GalleryUI::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: GalleryUI) {
        bundle.putParcelable(key, value)
    }

}

@Parcelize
data class GalleryUI(
    val id: Int,
    val name: String
) : Parcelable


abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}

data class Profile(val firstName:String, val lastName:String){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class ProfileArgType : JsonNavType<Profile>() {
        override fun fromJsonParse(value: String): Profile = Gson().fromJson(value, Profile::class.java)

    override fun Profile.getJsonParse(): String = Gson().toJson(this)

}