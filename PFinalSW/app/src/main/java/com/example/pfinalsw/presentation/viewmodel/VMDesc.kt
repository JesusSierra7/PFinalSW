package com.example.pfinalsw.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pfinalsw.domain.model.People

class VMDesc(application: Application) : AndroidViewModel(application) {

    val people : MutableLiveData<ArrayList<People>> by lazy {
        MutableLiveData<ArrayList<People>>()
    }

    val position : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}
