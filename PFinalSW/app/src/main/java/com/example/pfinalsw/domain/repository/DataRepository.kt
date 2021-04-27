package com.example.pfinalsw.domain.repository

import com.example.pfinalsw.domain.model.PeopleResponse
import retrofit2.Call
import retrofit2.Response

interface DataRepository {

    suspend fun getData(page : Int) : Response<PeopleResponse>
}