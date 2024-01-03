package com.mvvm.mvvm.api

import com.mvvm.mvvm.home.model.MovieItem
import com.mvvm.mvvm.utils.Constants
import retrofit2.Response
import retrofit2.http.GET


interface APIInterface {

    @GET(Constants.MOVIELIST_URL)
    suspend fun getAllMovies(): Response<List<MovieItem>>
}