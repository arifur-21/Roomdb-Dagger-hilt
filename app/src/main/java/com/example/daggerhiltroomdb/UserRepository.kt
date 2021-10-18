package com.example.daggerhiltroomdb


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    val getuserData: Flow<List<User>> = userDao.getUser()

    suspend fun insert(user: User) = withContext(Dispatchers.IO){
        userDao.insert(user)
    }
}