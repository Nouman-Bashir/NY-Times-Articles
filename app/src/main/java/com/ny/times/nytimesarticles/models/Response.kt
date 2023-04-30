package com.ny.times.nytimesarticles.models
import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response(
    @NonNull private val status: String,
    @NonNull private val copyright: String,
    @SerializedName("num_results") @NonNull private val numResults: Int,
    @NonNull private val results: List<MostPopular>
) : Serializable {
    @NonNull
    fun getStatus(): String {
        return status
    }

    @NonNull
    fun getCopyright(): String {
        return copyright
    }

    @NonNull
    fun getNumResults(): Int {
        return numResults
    }

    @NonNull
    fun getResults(): List<MostPopular> {
        return results
    }

    override fun toString(): String {
        return "Response{status='$status', copyright='$copyright', numResults=$numResults, results=$results}"
    }
}
