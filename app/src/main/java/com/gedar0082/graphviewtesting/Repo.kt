package com.gedar0082.graphviewtesting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Repo {
    var data = MutableLiveData<List<MyData>>()
    private val lData = mutableListOf<MyData>()

    fun insertData(mData: MyData){
        lData.add(mData)
        data.value = lData
    }

    fun getAllData(): LiveData<List<MyData>>{
        data.value = lData
        return data
    }

    fun delData(i: Int){
        lData.removeAt(i)
        data.value = lData
    }

    fun delAllData(){
        lData.clear()
        data.value = lData
    }
}