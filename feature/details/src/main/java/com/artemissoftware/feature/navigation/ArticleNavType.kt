package com.artemissoftware.feature.navigation

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import com.artemissoftware.newsroom.core.model.Article
import com.google.gson.Gson

val ArticleNavType: NavType<Article?> = object : NavType<Article?>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): Article? {
        return bundle.getString(key)?.let { parseValue(it) }
    }
    override fun parseValue(value: String): Article {
        return Gson().fromJson(value, Article::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: Article?) {
        bundle.putString(key, Gson().toJson(value))
    }
}

fun NavType<Article?>.getValue(savedStateHandle: SavedStateHandle, arg: String): Article? {
    return savedStateHandle.get<String>(arg)?.let {
        parseValue(it)
    }
}
