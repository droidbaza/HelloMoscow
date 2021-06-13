package com.db.data.repository

import android.util.Log
import com.rusatom.domain.LoadResult
import retrofit2.Response
import java.lang.Exception

object BaseRepository {

    inline fun <T : Any?> safeCall(responseCall: () -> Response<T>): LoadResult<T> {
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            val body = response.body()
            if (response.isSuccessful) {
                LoadResult.Success(body)
            } else {
                LoadResult.Error(responseCode, response.message())
            }
        } catch (e:Exception) {
            LoadResult.Error(500)
        }
    }
}