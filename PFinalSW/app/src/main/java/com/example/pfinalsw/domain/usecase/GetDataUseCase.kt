package com.example.pfinalsw.domain.usecase

import com.example.pfinalsw.domain.model.PeopleResponse
import com.example.pfinalsw.domain.repository.DataRepository
import com.example.pfinalsw.domain.usecase.base.UseCase
import retrofit2.Call
import retrofit2.Response

class GetDataUseCase constructor(
        private val dataRepository: DataRepository
) : UseCase<Response<PeopleResponse>, Any?>() {

    override suspend fun run(params: Any?): Response<PeopleResponse> {
        return dataRepository.getData(1)
    }

}