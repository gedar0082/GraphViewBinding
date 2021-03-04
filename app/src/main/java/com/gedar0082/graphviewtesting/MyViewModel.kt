package com.gedar0082.graphviewtesting

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(private val repo: Repo): ViewModel(), Observable {

    val list = repo.getAllData()
    var counter = 0

    @Bindable val name = MutableLiveData<String>()
    @Bindable val desc = MutableLiveData<String>()

    fun addNew(){
        val n: String = name.value ?: "someName"
        val d: String = desc.value ?: "someDesc"
        repo.insertData(MyData(counter++, 0, n, d))
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}