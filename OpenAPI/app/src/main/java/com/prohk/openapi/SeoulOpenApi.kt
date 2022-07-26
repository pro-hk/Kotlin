package com.prohk.openapi

import androidx.viewbinding.BuildConfig
import com.prohk.openapi.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object {
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = com.prohk.openapi.BuildConfig.api_key_library
    }
}

interface SeoulOpenService {
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/{end}")
    fun getLibraries(@Path("api_key") key:String, @Path("end") limit:Int): Call<Library>
}