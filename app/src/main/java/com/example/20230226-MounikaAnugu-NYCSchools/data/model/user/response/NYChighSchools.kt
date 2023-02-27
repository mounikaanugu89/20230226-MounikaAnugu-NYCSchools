package com.example.demomvvmflow.data.model.user.response


import com.google.gson.annotations.SerializedName

data class NYChighSchools(
        @SerializedName("dbn")
        val dbn: String?=null,
        @SerializedName("school_name")
        val school_name: String?=null,
        @SerializedName("conference")
        val conference: String,
        @SerializedName("division")
        val division: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
)