package com.example.pfinalsw.domain.usecase.base

import com.example.pfinalsw.domain.model.ApiError
import com.example.pfinalsw.domain.model.PeopleResponse
import retrofit2.Response

interface UseCaseResponse<Type> {

    fun onSuccess(result: Response<PeopleResponse>)

    fun onError(apiError: ApiError?)
}
