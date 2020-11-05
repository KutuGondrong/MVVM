package com.kutugondrong.kutugondronggithub.activity.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kutugondrong.kutugondronggithub.model.ResponseUsers
import com.kutugondrong.kutugondronggithub.network.helper.Resource
import com.kutugondrong.kutugondronggithub.network.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<ResponseUsers>>()
    val users: LiveData<Resource<ResponseUsers>>
        get() = _users

    init {
        //Kutugondrong.com
    }

    fun searchUser(search: String) {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            mainRepository.getUsers(search).let {
                if (it.isSuccessful) {
                    _users.postValue(Resource.success(it.body()))
                } else _users.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}