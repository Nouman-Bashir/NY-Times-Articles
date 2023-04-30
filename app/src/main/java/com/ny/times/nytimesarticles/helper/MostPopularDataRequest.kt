package com.ny.times.nytimesarticles.helper

import com.ny.times.nytimesarticles.interfaces.ApiEndpoints
import com.ny.times.nytimesarticles.models.Response
import com.ny.times.nytimesarticles.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MostPopularDataRequest {
    private val apiEndpoints: ApiEndpoints = RestClient.createService(ApiEndpoints::class.java)

    fun getMostPopularArticles(): Observable<Response> {
        val data: MutableMap<String, String> = HashMap()
        data["api-key"] = Constants.API_KEY

        return apiEndpoints.getMostPopularArticles(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
