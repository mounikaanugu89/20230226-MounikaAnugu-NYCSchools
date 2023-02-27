package com.example.demomvvmflow.repository

import com.example.demomvvmflow.data.base.remote.BaseApi
import com.example.demomvvmflow.data.model.user.response.NYChighSchools
import com.example.demomvvmflow.data.model.user.response.SatResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class NYCSchoolRepository(private val baseApi: BaseApi) {
    suspend fun getNYCSchoolNames(): Flow<List<NYChighSchools>> {
        return flow {
            val userList = baseApi.getNYCSchoolNames()
            emit(userList)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getSatResult(): Flow<List<SatResult>> {
        return flow {
            val userList = baseApi.getSatResult()
            emit(userList)
        }.flowOn(Dispatchers.IO)
    }
}