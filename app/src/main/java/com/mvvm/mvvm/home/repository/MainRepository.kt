package com.mvvm.mvvm.home.repository

import com.mvvm.mvvm.api.RetrofitInstance

class MainRepository constructor(private val retrofitService: RetrofitInstance) {

    suspend fun getAllMovies() = retrofitService.instance?.apiInterface?.getAllMovies()

}