package com.animescrap.feature.episodedownload.helper

fun String.transformStringEpisodeInNumber(): Int{
    return this.replaceBefore("Episódio", "")
        .replace("Episódio", "")
        .trim()
        .toInt()
}