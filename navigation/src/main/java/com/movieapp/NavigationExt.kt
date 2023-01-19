package com.movieapp


fun String.getMainPath(): String {
    return this.split("/").firstOrNull() ?: this
}

fun generateDestinationArguments(path: String, vararg args: String): String {
    val mainPath = path.getMainPath()

    return buildString {
        append(mainPath)
        args.forEach {
            append("/$it")
        }
    }
}