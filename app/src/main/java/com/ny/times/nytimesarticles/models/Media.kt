package com.ny.times.nytimesarticles.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Media(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    @SerializedName("approved_for_syndication") val approvedForSyndication: Int,
    @SerializedName("media-metadata") val mediaMetaData: List<MediaMetaData>
) : Serializable {
    override fun toString(): String {
        return "Media{" +
                "type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", caption='" + caption + '\'' +
                ", copyright='" + copyright + '\'' +
                ", approvedForSyndication=" + approvedForSyndication +
                ", mediaMetaData=" + mediaMetaData +
                '}'
    }
}
