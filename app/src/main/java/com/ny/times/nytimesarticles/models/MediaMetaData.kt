package com.ny.times.nytimesarticles.models

import java.io.Serializable

class MediaMetaData(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int
) : Serializable {

    override fun toString(): String {
        return "MediaMetaData{" +
                "url='" + url + '\'' +
                ", format='" + format + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}'
    }
}
