package com.example.pfinalsw.data.source.remote

import com.example.pfinalsw.domain.model.PeopleResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //Describe the request type and the relative URL//
    @GET("people/")
    suspend fun getData(@Query("page") page : Int) : Response<PeopleResponse>
}