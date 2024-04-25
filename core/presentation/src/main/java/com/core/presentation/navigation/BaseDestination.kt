package com.core.presentation.navigation

import android.net.Uri
import androidx.navigation.NamedNavArgument
import com.google.gson.Gson

abstract class BaseDestination(
    private val route: String,
    private val customArguments: List<NamedNavArgument> = emptyList(),
) {

    fun fullRoute(): String {
        return if (customArguments.isEmpty()) route else completeRoute
    }

    private val completeRoute: String = buildString {
        append(route)
        customArguments.forEachIndexed { index, custom ->
            val symbol = if (index == 0) "?" else "&"
            append("$symbol${custom.name}={${custom.name}}")
        }
    }

    val arguments = customArguments

    fun withCustomArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, arg ->
                val json = Uri.encode(Gson().toJson(arg))
                val symbol = if (index == 0) "?" else "&"
                append("$symbol${customArguments[index].name}=$json")
            }
        }
    }
}
