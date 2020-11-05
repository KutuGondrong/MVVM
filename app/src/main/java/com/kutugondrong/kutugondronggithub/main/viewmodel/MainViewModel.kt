package com.kutugondrong.kutugondronggithub.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kutugondrong.kutugondronggithub.model.User
import com.kutugondrong.kutugondronggithub.network.helper.Resource
import com.kutugondrong.kutugondronggithub.network.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            mainRepository.getUsers().let {
                if (it.isSuccessful) {
                    _users.postValue(Resource.success(it.body()))
                } else _users.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}