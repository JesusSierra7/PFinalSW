package com.example.pfinalsw.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfinalsw.domain.model.ApiError
import com.example.pfinalsw.domain.model.People
import com.example.pfinalsw.domain.model.PeopleResponse
import com.example.pfinalsw.domain.usecase.GetDataUseCase
import com.example.pfinalsw.domain.usecase.base.UseCaseResponse
import kotlinx.coroutines.cancel
import retrofit2.Response

class VMMain constructor(private val getDataUseCase: GetDataUseCase) : ViewModel() {

    val pList : MutableLiveData<ArrayList<People>> by lazy {
        MutableLiveData<ArrayList<People>>()
    }

    fun getData() {
        getDataUseCase.invoke(viewModelScope, null, object : UseCaseResponse<Response<PeopleResponse>> {
            override fun onSuccess(result: Response<PeopleResponse>) {
                for (i in 0..(result.body()!!.results.size)) {
                    pList.value = result.body()!!.results
                }
            }

            override fun onError(apiError: ApiError?) {
                apiError?.getErrorMessage()
            }

            }
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = VMMain::class.java.name
    }

}

