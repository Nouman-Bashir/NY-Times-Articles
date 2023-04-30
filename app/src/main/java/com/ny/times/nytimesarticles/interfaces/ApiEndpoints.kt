package com.ny.times.nytimesarticles.interfaces

import com.ny.times.nytimesarticles.models.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiEndpoints {
    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    fun getMostPopularArticles(@QueryMap options: Map<String, String>): Observable<Response>
}
