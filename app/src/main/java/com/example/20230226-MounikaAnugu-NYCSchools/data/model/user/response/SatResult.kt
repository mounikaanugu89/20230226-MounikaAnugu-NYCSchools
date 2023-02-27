package com.example.demomvvmflow.data.model.user.response


import com.google.gson.annotations.SerializedName

data class SatResult(
    @SerializedName("dbn")
    val dbn: String? = null,
    @SerializedName("school_name")
    val school_name: String? = null,
    @SerializedName("num_of_sat_test_takers")
    val num_of_sat_test_takers: String? = null,
    @SerializedName("sat_critical_reading_avg_score")
    val sat_critical_reading_avg_score: String? = null,
    @SerializedName("sat_math_avg_score")
    val sat_math_avg_score: String? = null,
    @SerializedName("sat_writing_avg_score")
    val sat_writing_avg_score: String? = null
)