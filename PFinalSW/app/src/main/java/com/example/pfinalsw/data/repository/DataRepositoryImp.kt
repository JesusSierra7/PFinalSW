package com.example.pfinalsw.data.repository

import com.example.pfinalsw.data.source.remote.ApiService
import com.example.pfinalsw.domain.model.PeopleResponse
import com.example.pfinalsw.domain.repository.DataRepository
import retrofit2.Call
import retrofit2.Response

class DataRepositoryImp(private val apiService: ApiService) : DataRepository{

    override suspend fun getData(page : Int) : Response<PeopleResponse> {
        return apiService.getData(page)
    }
}