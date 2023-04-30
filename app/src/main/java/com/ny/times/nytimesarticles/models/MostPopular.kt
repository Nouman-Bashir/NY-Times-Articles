package com.ny.times.nytimesarticles.models
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class MostPopular(
    val url: String,
    @SerializedName("adx_keywords") val adxKeywords: String,
    var column: String,
    var section: String,
    var byline: String,
    var type: String,
    var title: String,
    @SerializedName("abstract") var abs: String,
    @SerializedName("published_date") var publishedDate: String,
    var source: String,
    var id: String,
    @SerializedName("asset_id") var assetId: String,
    var views: Int,
    var media: List<Media>
) : Serializable {
    override fun toString(): String {
        return "MostPopular{" +
                "url='" + url + '\'' +
                ", adxKeywords='" + adxKeywords + '\'' +
                ", column='" + column + '\'' +
                ", section='" + section + '\'' +
                ", byline='" + byline + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", abs='" + abs + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", source='" + source + '\'' +
                ", id='" + id + '\'' +
                ", assetId='" + assetId + '\'' +
                ", views=" + views +
                ", media=" + media +
                '}'
    }
}