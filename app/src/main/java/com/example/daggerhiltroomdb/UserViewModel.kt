package com.example.daggerhiltroomdb

import androidx.lifecycle.*

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(val userRepository: UserRepository): ViewModel(){


    ///flow to live data convert
    val getUserData: LiveData<List<User>> get() = userRepository.getuserData
        .flowOn(Dispatchers.IO).asLiveData(context = viewModelScope.coroutineContext)

    fun insert(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }
}

