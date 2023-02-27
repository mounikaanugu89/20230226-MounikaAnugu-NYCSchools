package com.example.demomvvmflow.data.base.remote

import com.example.demomvvmflow.data.model.user.response.NYChighSchools
import com.example.demomvvmflow.data.model.user.response.SatResult
import com.example.demomvvmflow.util.API_NYC_SCHOOL_LIST
import com.example.demomvvmflow.util.API_SAT_RESULT
import retrofit2.http.GET

interface BaseApi {
    @GET(API_NYC_SCHOOL_LIST)
    suspend fun getNYCSchoolNames(): List<NYChighSchools>

    @GET(API_SAT_RESULT)
    suspend fun getSatResult(): List<SatResult>
}