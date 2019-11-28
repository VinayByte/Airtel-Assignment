package com.test.airtel.data.source.remote

import com.test.airtel.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
interface ApiInterface {

    @GET("compassLocation/rest/address/autocomplete?")
     fun getSearchList(@Query("queryString") provider: String, @Query("city") city: String): Observable<Response>
}