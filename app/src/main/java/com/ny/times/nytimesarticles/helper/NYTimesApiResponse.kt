package com.ny.times.articles.helper

import android.os.Parcel
import android.os.Parcelable


data class NYTimesApiResponse(
    val status: String,
    val results: List<Article>
) {
    data class Article(
        val id: Long,
        val title: String,
        val abstract: String,
        val url: String,
        val publishedDate: String,
        val media: List<Media>
    ) : Parcelable {

        constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.createTypedArrayList(Media) ?: emptyList()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeLong(id)
            parcel.writeString(title)
            parcel.writeString(abstract)
            parcel.writeString(url)
            parcel.writeString(publishedDate)
            parcel.writeTypedList(media)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Article> {
            override fun createFromParcel(parcel: Parcel): Article {
                return Article(parcel)
            }

            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Media(
        val type: String,
        val caption: String,
        val url: String
    ) : Parcelable {

        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(type)
            parcel.writeString(caption)
            parcel.writeString(url)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Media> {
            override fun createFromParcel(parcel: Parcel): Media {
                return Media(parcel)
            }

            override fun newArray(size: Int): Array<Media?> {
                return arrayOfNulls(size)
            }
        }
    }
            data class MediaMetadata(
                val url: String,
                val format: String,
                val height: Int,
                val width: Int
            )


}
